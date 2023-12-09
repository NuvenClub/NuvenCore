package com.emanuelvini.nuven.core.bungee.registry.registries

import com.emanuelvini.nuven.core.bungee.BungeeMain
import com.emanuelvini.nuven.core.bungee.listener.packet.ProfilePacketListener
import com.emanuelvini.nuven.core.bungee.registry.BungeeRegistry

class PacketListenerRegistry(plugin: BungeeMain) : BungeeRegistry(plugin) {
    override fun register() {
        plugin.bungeeLogger.success("Conectado a database com sucesso.")
        plugin.proxy.registerChannel("nvcore:main")
        plugin.proxy.pluginManager
            .registerListener(plugin, ProfilePacketListener(plugin.proxy, plugin.proxy.scheduler, plugin.profileRepository!!, plugin ))
        plugin.bungeeLogger.success("Canais de comunicações registrados com sucesso.")
    }
}
