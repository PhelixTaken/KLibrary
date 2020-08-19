package me.phelix.klibrary.files.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.phelix.klibrary.extensions.*
import me.phelix.klibrary.files.gson.adapters.InventoryTypeAdapter
import me.phelix.klibrary.files.gson.adapters.LocationTypeAdapter
import net.prosavage.baseplugin.serializer.Persist
import org.bukkit.Location
import org.bukkit.inventory.Inventory
import java.io.File
import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.reflect.Type
import java.nio.file.Files
import java.nio.file.Path
import java.util.logging.Logger

class Persist(private val dataFolder: File) {

    private val gson = buildGson().create()
    private val gsonData = buildDataGson().create()

    fun <T> load(type: Type, path: Path): T {
        val content = path.readString()
        try {
            return gson.fromJson(content, type)
        } catch (e: Exception) {
            throw IllegalStateException("Could not load on path ${path.toUri()}", e)
        }
    }

    fun save(data: Boolean, instance: Any, path: Path) {
        val gson = if(data) gsonData else this.gson
        try {
            if(!path.exists())
                path.createFile()
            path.write(gson.toJson(instance).toByteArray())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun save(data: Boolean, instance: Any, path: String) {
        save(data, instance, Path.of(".${File.separator}$path"))
    }

    fun saveDataFolder(data: Boolean, instance: Any, path: String) {
        val dataPath = Path.of(".${File.separator}$dataFolder")
        if(!dataPath.exists())
            dataPath.createDirectory()
        val newPath = Path.of(".${File.separator}${dataFolder}${File.separator}$path")
        save(data, instance, newPath)
    }

    private fun buildGson(): GsonBuilder {
        return GsonBuilder().setPrettyPrinting().disableHtmlEscaping().enableComplexMapKeySerialization().excludeFieldsWithModifiers(
            128, 64).registerTypeAdapter(Location::class.java, LocationTypeAdapter()).registerTypeAdapter(Inventory::class.java, InventoryTypeAdapter())
    }


    private fun buildDataGson(): GsonBuilder {
        return GsonBuilder().disableHtmlEscaping().enableComplexMapKeySerialization().excludeFieldsWithModifiers(
            128, 64).registerTypeAdapter(Location::class.java, LocationTypeAdapter()).registerTypeAdapter(Inventory::class.java, InventoryTypeAdapter())
    }


}