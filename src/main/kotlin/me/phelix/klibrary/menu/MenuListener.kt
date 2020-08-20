package me.phelix.klibrary.menu

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.InventoryHolder

class MenuListener : Listener {

    @EventHandler(ignoreCancelled = true)
    fun onClick(event: InventoryClickEvent) {
        val holder: InventoryHolder = event.inventory.holder!!
        if(holder !is Menu) return
        event.isCancelled = holder.onClick(event.whoClicked as Player, event.slot, event.currentItem, event.click)
    }

    @EventHandler
    fun onOpen(event: InventoryOpenEvent) {
        val holder: InventoryHolder = event.inventory.holder!!
        if(holder is Menu) holder.onOpen(event.player as Player)
    }

    @EventHandler
    fun onClose(event: InventoryCloseEvent) {
        val holder: InventoryHolder = event.inventory.holder!!
        if(holder is Menu) holder.onClose(event.player as Player)
    }
}