package com.emanuelvini.nuven.core.bungee.registry.registries

import com.emanuelvini.nuven.core.bungee.BungeeMain
import com.emanuelvini.nuven.core.bungee.data.repository.ProfileRepository
import com.emanuelvini.nuven.core.bungee.listener.packet.PacketUtil
import com.emanuelvini.nuven.core.bungee.registry.BungeeRegistry
import com.emanuelvini.nuven.core.shared.player.profile.util.ProfileUtil
import com.emanuelvini.nuven.core.shared.registry.Registry
import com.emanuelvini.nuven.core.shared.player.ranking.Role
import net.luckperms.api.LuckPermsProvider
import net.luckperms.api.event.node.NodeRemoveEvent
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.config.Configuration
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration
import java.io.File
import java.nio.file.Files
import java.util.*

class RoleRegistry(plugin: BungeeMain, private val profileRepository: ProfileRepository) : BungeeRegistry(plugin) {

    override fun register() {
        super.register()
        val rolesFile = File(plugin.settingsFolder, "roles.yml")
        if (!rolesFile.exists()) {
            Files.copy(plugin.getResourceAsStream("settings/roles.yml"), rolesFile.toPath())
        }
        val logger = plugin.bungeeLogger
        val roleManager = plugin.roleManager
        val rolesConfig = ConfigurationProvider
            .getProvider(YamlConfiguration::class.java)
            .load(rolesFile)
        val s = rolesConfig.getSection("roles")
        s.keys.forEach { key ->
            try {
                val role = loadRole(s.getSection(key))
                roleManager.roles[role.order] = role
                logger.success(
                    "[ROLES]${ChatColor.GRAY} Cargo ${role.prefix} carregado com sucesso!"
                )
            } catch (e: Exception) {
                logger.warn(
                    "Houve um erro ao carregar o cargo %s, por favor verifique a configuração."

                )
                e.printStackTrace()
            }
        }

        val eventBus = LuckPermsProvider.get().eventBus
        eventBus.subscribe(plugin, NodeRemoveEvent::class.java) {
            if (it.isUser) {
                val player : ProxiedPlayer? = plugin.proxy.getPlayer(UUID.fromString(it.target.identifier.name))
                if (player != null) {
                    val profile = profileRepository.getData(player.name)
                    profile.role = roleManager.available(player).last()
                    PacketUtil.sendGetProfile(profile, player)
                }

            }
        }
    }

    private fun loadRole(section: Configuration): Role {
        return Role(
            ChatColor.translateAlternateColorCodes('&',section.getString("name")),
            ChatColor.translateAlternateColorCodes('&', section.getString("prefix")),
           section.getString("permission"),
            section.getInt("order"),
            section.getBoolean("always_invisible"),
            section.getBoolean("special_role"),
            section.getBoolean("broadcast"),
            section.getBoolean("colored_chat")
        )
    }
}
