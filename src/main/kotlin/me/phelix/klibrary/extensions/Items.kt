package me.phelix.klibrary.extensions

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

inline fun item(
        material: Material,
        amount: Int = 1,
        meta: ItemMeta.() -> Unit = {}
): ItemStack = ItemStack(material, amount).meta(meta)

inline fun <reified T : ItemMeta> ItemStack.meta(
        block: T.() -> Unit
): ItemStack = apply {
    itemMeta = (itemMeta as? T)?.apply(block) ?: itemMeta
}
fun ItemStack.displayName(displayName: String): ItemStack = meta<ItemMeta> {
    setDisplayName(displayName)
}

//val item = item(Material.DIAMOND).apply {
//    amount = 5
//    meta<ItemMeta> {
//        displayName("Sd")
//        lore = arrayListOf("j")
//    }
//}
