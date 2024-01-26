package com.emanuelvini.nuven.core.bukkit.registry.registries

import com.emanuelvini.nuven.core.bukkit.BukkitMain
import com.emanuelvini.nuven.core.bukkit.registry.BukkitRegistry
import com.emanuelvini.nuven.core.shared.player.ranking.Role
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.Team

class TabRegistry(plugin: BukkitMain) : BukkitRegistry(plugin) {

    private val server = plugin.server

    private val scoreboard = server.scoreboardManager.newScoreboard

    private val scoreboardManager = server.scoreboardManager

    private val profileRequester = plugin.profileRequester

    private val tabManager = TabManager()
    override fun register() {
        tabManager.enable()
        plugin.bukkitLogger.success("O sistema de TAB foi ativado com sucesso.")
    }
    fun disable(reason : String) {
        tabManager.disable()
        plugin.bukkitLogger.warn(reason)
    }
    fun disable(plugin : JavaPlugin) {
        disable("O sistema de TAB foi desativado pelo plugin ${ChatColor.WHITE}${plugin.name}")
    }

    private inner class TabManager {

        private val teams : HashMap<String, Team> = HashMap()

        private val tabListener = TabListener()
        private fun setup(role : Role, player : Player) {
            val team = try {
                teams[role.name]!!
            } catch (ex : Exception) {


                val team = scoreboard.registerNewTeam(('a' + (26 - role.order)).toString())

                team
            }
            teams[role.name] = team
            teams.values.forEach {
                it.removeEntry(player.name)
            }
            team.addEntry(player.name)
            team.prefix = role.prefix
            println(role.prefix)
            player.scoreboard = scoreboard

        }

        fun enable() {
            server.pluginManager.registerEvents(TabListener(), plugin)
        }
        fun disable() {
            HandlerList.unregisterAll(tabListener)
        }
        private inner class TabListener : Listener {

            @EventHandler
            fun onJoin(event: PlayerJoinEvent) {
                profileRequester.waitReceive(
                    event.player.name
                ) {
                    val role = it!!.role
                    setup(role, event.player)
                }
                profileRequester.requestData(event.player.name)
            }

        }

    }

}