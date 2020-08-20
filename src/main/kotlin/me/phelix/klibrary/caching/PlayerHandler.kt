package me.phelix.klibrary.caching

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

class PlayerHandler <V> {

    val map = mutableMapOf<UUID, V>()

    fun getPlayer(name: String): Player? = map[Bukkit.getPlayer(name)?.uniqueId] as Player
    fun getPlayer(id: UUID): Player? = map[Bukkit.getPlayer(id)?.uniqueId] as Player
    fun getPlayerStringId(id: String): Player? = map[Bukkit.getPlayer(UUID.fromString(id))?.uniqueId] as Player

}