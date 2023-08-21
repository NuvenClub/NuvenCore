package com.emanuelvini.nuven.core.bungee.registry;

import com.emanuelvini.nuven.core.bungee.BungeeMain;
import com.emanuelvini.nuven.core.bungee.packet.ProfilePacketListener;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PacketListenerRegistry {

    private BungeeMain plugin;

    public void register() {
        plugin.getProxy().getPluginManager().registerListener(plugin, new ProfilePacketListener(plugin.getProfileRepository(), plugin));
    }

}
