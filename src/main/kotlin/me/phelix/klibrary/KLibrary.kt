package me.phelix.klibrary

import me.phelix.klibrary.menu.MenuListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class KLibrary : JavaPlugin() {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(MenuListener(), this)
    }

}