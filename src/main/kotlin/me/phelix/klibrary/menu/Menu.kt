package me.phelix.klibrary.menu

import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

interface Menu : InventoryHolder {

    fun onClick(player: Player, slot: Int, item: ItemStack?, type: ClickType): Boolean { return true }
    fun onOpen(player: Player) {}
    fun onClose(player: Player) {}

}