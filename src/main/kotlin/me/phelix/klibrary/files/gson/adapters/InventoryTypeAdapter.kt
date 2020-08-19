package me.phelix.klibrary.files.gson.adapters

import com.google.gson.*
import org.bukkit.Bukkit
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.util.io.BukkitObjectInputStream
import org.bukkit.util.io.BukkitObjectOutputStream
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.lang.IllegalStateException
import java.lang.reflect.Type

class InventoryTypeAdapter : JsonSerializer<Inventory>, JsonDeserializer<Inventory> {

    override fun serialize(inventory: Inventory, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        val jsonObject = JsonObject()
        jsonObject.add("contents", JsonPrimitive(toBase64(inventory)))
        return jsonObject
    }

    override fun deserialize( json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Inventory? {
        val jsonObject = json.asJsonObject
        return fromBase64(jsonObject.get("contents").asString)
    }

    fun inventoryToString(items: Array<ItemStack>): String {
        try {
            val outputStream = ByteArrayOutputStream()
            val dataOutput = BukkitObjectOutputStream(outputStream)
            dataOutput.writeInt(items.size)

            items.forEach { dataOutput.writeObject(it) }
            dataOutput.close()
            return Base64Coder.encodeLines(outputStream.toByteArray())
        } catch (e: Exception) {
            throw IllegalStateException("Unable to save item stacks.", e)
        }
    }

    fun toBase64(inventory: Inventory): String {
        try {
            val outputStream = ByteArrayOutputStream()
            val dataOutput = BukkitObjectOutputStream(outputStream)
            dataOutput.writeInt(inventory.size)

            for(i in 0 until inventory.size) {
                dataOutput.writeObject(inventory.getItem(i))
            }

            dataOutput.close()
            return Base64Coder.encodeLines(outputStream.toByteArray())
        } catch (e: Exception) {
            throw IllegalStateException("Cannot into itemstacks!", e)
        }
    }

    fun fromBase64(data: String): Inventory? {
        try {
            val inputStream = ByteArrayInputStream(Base64Coder.decodeLines(data))
            val dataInput = BukkitObjectInputStream(inputStream)
            val inventory = Bukkit.getServer().createInventory(null, dataInput.readInt())

            for(i in 0 until inventory.size) {
                inventory.setItem(i, dataInput.readObject() as ItemStack)
            }

            dataInput.close()
            return inventory
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

}