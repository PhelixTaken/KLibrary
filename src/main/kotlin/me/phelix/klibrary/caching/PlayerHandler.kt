package me.phelix.klibrary.caching

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

class PlayerHandler <V> {

    val map = mutableMapOf<UUID, V>()

    fun getPlayer(name: String): V? = map[Bukkit.getPlayer(name)?.uniqueId]
    fun getPlayer(id: UUID): V? = map[Bukkit.getPlayer(id)?.uniqueId]
    fun getPlayer(player: Player): V? = map[player.uniqueId]
    fun getPlayerStringId(id: String): V? = map[Bukkit.getPlayer(UUID.fromString(id))?.uniqueId]

}