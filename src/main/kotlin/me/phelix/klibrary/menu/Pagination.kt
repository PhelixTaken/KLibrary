package me.phelix.klibrary.menu

import org.bukkit.Material
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*
import kotlin.math.min

class Pagination<T> (private val list: List<T>, var size: Int, private val inventory: Inventory) {

    private var page: Int = 0

    fun next(pre: Int, ne: Int) {
        if (getPage(page + 1).isNotEmpty()) {
            inventory.clear()
            page++
        }

        if (getPage(page + 1).isNotEmpty()) inventory.setItem(ne, bookItem(true))
        if (page != 1)
            inventory.setItem(pre, bookItem(false))

        var i = 0
        getPage(page).forEach {
            inventory.setItem(i, it as ItemStack)
            i++
        }
    }

    fun previous(pre: Int, ne: Int) {
        if (page - 1 > 0) {
            inventory.clear()
            page--
        }

        if (page != 1)
            inventory.setItem(pre, bookItem(false))

        var i = 0
        getPage(page).forEach {
            inventory.setItem(i, it as ItemStack)
            i++
        }
        inventory.setItem(ne, bookItem(true))
    }

    private fun bookItem(next: Boolean): ItemStack {
        val item = ItemStack(Material.BOOK)
        val meta = item.itemMeta
        meta.setDisplayName(if (next) "Next" else "Previous")
        item.itemMeta = meta
        return item
    }

    fun getPage(page: Int): List<T> {
        if (size <= 0 || page <= 0)
            throw IllegalArgumentException("Invalid page/pagesize: ${page}/${size}")

        val fromIndex = (page - 1) * size
        if (list.size < fromIndex) return Collections.emptyList()

        return list.subList(fromIndex, min(fromIndex + size, list.size))
    }
}
