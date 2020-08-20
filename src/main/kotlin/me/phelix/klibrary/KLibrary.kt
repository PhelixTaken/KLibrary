package me.phelix.klibrary

import me.phelix.klibrary.menu.MenuListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class KLibrary : JavaPlugin() {

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(MenuListener(), this)
        var contains = false
        val list = mutableListOf<String>()
        Bukkit.getPluginManager().plugins.forEach {
            if (it.description.depend.contains(name) || it.description.softDepend.contains(name)) {
                contains = true
                list.add(it.name)
            }
        }

        if (contains) {
            println("KLibrary has found plugin(s) that use this as dependency")
            list.forEach {
                println("- $it")
            }
        } else {
            println("KLibrary has not found any plugin using this as dependency so we are disabling this library to save performance!")
            Bukkit.getPluginManager().disablePlugin(this)
        }

    }

}