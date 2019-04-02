package ywxt.myswjtu.managers

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Context.MODE_WORLD_WRITEABLE
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
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
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

    fun getSharedPreferencesInt(name: String): Int =
        context.getSharedPreferences(name, MODE_PRIVATE).getInt(name, 0)


    fun writeSharedPreferences(name: String, commit: Boolean = false, edit: SharedPreferences.Editor.() -> Unit) {
        context.getSharedPreferences(name, MODE_PRIVATE).edit(commit, edit)
    }


    fun getStream(fileName: String): Flowable<InputStream> {
        return Flowable.create<InputStream>({
            val stream: InputStream = context.openFileInput(fileName)
            it.onNext(stream)
            
        }, BackpressureStrategy.BUFFER)
            .subscribeOn(Schedulers.io())
    }

    /**
     * 小文件
     */
    fun getByteArray(fileName: String): Flowable<ByteArray> {
        return getStream(fileName)
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
    fun getString(fileName: String): Flowable<String> {
        return getByteArray(fileName)
            .observeOn(Schedulers.computation())
            .map {
                String(it)
            }
    }

    fun writeString(fileName: String, content: String): Flowable<Boolean> {
        return writeStream(fileName)
            .observeOn(Schedulers.io())
            .map {
                it.write(content.toByteArray())
                it.close()
                true
            }
    }

    fun writeStream(fileName: String): Flowable<OutputStream> {
        return Flowable.create<OutputStream>(
            {
                val stream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
                it.onNext(stream)
            }, BackpressureStrategy.BUFFER
        ).subscribeOn(Schedulers.io())
    }

    /**
     * 小图片
     */
    fun getBitmap(fileName: String): Flowable<Bitmap> {
        return getStream(fileName)
            .observeOn(Schedulers.io())
            .map {
                val bitmap = BitmapFactory.decodeStream(it)
                it.close()
                bitmap
            }

    }
}