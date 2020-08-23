package me.phelix.klibrary.files.gson

import com.google.gson.GsonBuilder
import me.phelix.klibrary.extensions.*
import me.phelix.klibrary.files.gson.adapters.InventoryTypeAdapter
import me.phelix.klibrary.files.gson.adapters.LocationTypeAdapter
import org.bukkit.Location
import org.bukkit.inventory.Inventory
import java.io.File
import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.reflect.Type
import java.nio.file.Path

class Persist(private val dataFolder: File) {

    private val gson = buildGson().create()
    private val gsonData = buildDataGson().create()

    /** Load a json file
     * @param type The type you want to deserialize to
     * @param path The path u want to deserialize
     */
    fun <T> load(type: Type, path: Path): T {
        val content = path.readString()
        try {
            return gson.fromJson(content, type)
        } catch (e: Exception) {
            throw IllegalStateException("Could not load on path ${path.toUri()}", e)
        }
    }

    /** Saves to a json file
     * @param data If you want data
     * @param instance What you want to serialize
     * @param path The path
     */
    fun save(data: Boolean, instance: Any, path: Path) {
        val newGson = if(data) gsonData else this.gson
        try {
            if(!path.exists())
                path.createFile()
            path.write(newGson.toJson(instance).toByteArray())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /** Saves to a json file
     * @param data If you want data
     * @param instance What you want to serialize
     * @param path The path that starts where the jar is located
     */
    fun save(data: Boolean, instance: Any, path: String) {
        save(data, instance, Path.of(".${File.separator}$path"))
    }

    /** Saves to a json file
     * @param data If you want data
     * @param instance What you want to serialize
     * @param path The path that starts where data folder of a spigot plugin is located
     */
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