package me.phelix.klibrary.extensions

import org.bukkit.ChatColor
import org.bukkit.entity.Player

fun Player.msg(message: String) = this.sendMessage(ChatColor.translateAlternateColorCodes('&', message))