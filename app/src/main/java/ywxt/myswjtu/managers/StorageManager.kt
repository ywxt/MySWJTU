package ywxt.myswjtu.managers

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import me.ghui.fruit.reflect.TypeToken
import ywxt.myswjtu.models.MainModuleModel
import java.io.InputStream
import java.util.stream.Stream

class StorageManager(
    private val context: Context
) {

    fun writeSharedPreferences(name: String, data: Map<String, *>, commit: Boolean = false) {
        context.getSharedPreferences(name, MODE_PRIVATE).edit(commit) {
            for (item in data) {
                when (item.value) {
                    is Boolean -> putBoolean(item.key, item.value as Boolean)
                    is Float -> putFloat(item.key, item.value as Float)
                    is String -> putString(item.key, item.value as String)
                    is Int -> putInt(item.key, item.value as Int)
                    is Long -> putLong(item.key, item.value as Long)
                }
            }
        }

    }

    fun writeSharedPreferences(name: String, commit: Boolean = false, edit: SharedPreferences.Editor.() -> Unit) {
        context.getSharedPreferences(name, MODE_PRIVATE).edit(commit, edit)
    }
    

    fun getStreamFromAssets(fileName: String): Flowable<InputStream> {
        return Flowable.create<InputStream>({
            val stream = context.resources.assets.open(fileName)
            it.onNext(stream)
        }, BackpressureStrategy.BUFFER)
            .subscribeOn(Schedulers.io())
    }

    /**
     * 小文件
     */
    fun getByteArrayFromAssets(fileName: String): Flowable<ByteArray> {
        return getStreamFromAssets(fileName)
            .observeOn(Schedulers.io())
            .map {
                val length = it.available()
                val buffer = ByteArray(length)
                it.read(buffer)
                it.close()
                buffer
            }
    }


    /**
     * 小文件
     */
    fun getStringFromAsssets(fileName: String): Flowable<String> {
        return getByteArrayFromAssets(fileName)
            .observeOn(Schedulers.computation())
            .map {
                String(it)
            }
    }

    /**
     * 小图片
     */
    fun getBitmapFromAssets(fileName: String): Flowable<Bitmap> {
        return getStreamFromAssets(fileName)
            .observeOn(Schedulers.io())
            .map {
                val bitmap = BitmapFactory.decodeStream(it)
                it.close()
                bitmap
            }

    }
}