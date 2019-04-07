package ywxt.myswjtu.common.typeadapters

import com.google.gson.*
import okhttp3.Cookie
import java.lang.reflect.Type

class CookieAdapter : JsonSerializer<Cookie>, JsonDeserializer<Cookie> {
    override fun serialize(src: Cookie?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        if (src == null) return JsonObject()
        return JsonObject().apply {
            addProperty("domain", src.domain())
            addProperty("name", src.name())
            addProperty("value", src.value())
        }
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Cookie {
        return try {
            val cookie = json as JsonObject
            Cookie.Builder()
                .domain(cookie.getAsJsonPrimitive("domain").asString)
                .name(cookie.getAsJsonPrimitive("name").asString)
                .value(cookie.getAsJsonPrimitive("value").asString)
                .build()
        } catch (e: Exception) {
            Cookie.Builder().domain("").name("").value("").build()
        }
    }

}