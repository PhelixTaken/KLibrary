package me.phelix.klibrary.files.gson.adapters

import com.google.gson.*
import org.bukkit.Bukkit
import org.bukkit.Location
import java.lang.Exception
import java.lang.reflect.Type

class LocationTypeAdapter : JsonSerializer<Location>, JsonDeserializer<Location> {

    override fun serialize(location: Location, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val jsonObject = JsonObject()

        try {
            jsonObject.add("x",  JsonPrimitive(location.x))
            jsonObject.add("y",  JsonPrimitive(location.y))
            jsonObject.add("z",  JsonPrimitive(location.z))
            jsonObject.add("world",  JsonPrimitive(location.world.name))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return jsonObject
    }

    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Location? {
        val jsonObject = json.asJsonObject

        try {
            return Location(Bukkit.getWorld(jsonObject.get("world").asString), jsonObject.get("x").asDouble, jsonObject.get("y").asDouble, jsonObject.get("z").asDouble)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}

