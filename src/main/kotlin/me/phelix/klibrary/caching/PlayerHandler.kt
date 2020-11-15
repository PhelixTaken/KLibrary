package me.phelix.klibrary.caching

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

/**
 * @param player If you are gonna use the Player object as key or not. If yes, then use an [IdentityHashMap] or else
 * use a [MutableMap]
 */
class PlayerHandler <V>(player: Boolean) {

    val map = if(player) IdentityHashMap<Player, V>() else mutableMapOf<UUID, V>()

    fun getPlayer(name: String): V? = map[Bukkit.getPlayer(name)?.uniqueId]
    fun getPlayer(id: UUID): V? = map[Bukkit.getPlayer(id)?.uniqueId]
    fun getPlayer(player: Player): V? = map[player.uniqueId]
    fun getPlayerStringId(id: String): V? = map[Bukkit.getPlayer(UUID.fromString(id))?.uniqueId]

}