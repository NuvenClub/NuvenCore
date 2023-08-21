package com.emanuelvini.nuven.core.bungee.packet;


import com.emanuelvini.nuven.core.bungee.BungeeMain;
import com.emanuelvini.nuven.core.bungee.data.repository.ProfileRepository;
import com.emanuelvini.nuven.core.shared.player.profile.util.ProfileUtil;
import com.google.common.io.ByteStreams;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.scheduler.TaskScheduler;
import net.md_5.bungee.event.EventHandler;


@AllArgsConstructor
public class ProfilePacketListener implements Listener {

    private final ProxyServer proxy = ProxyServer.getInstance();
    private final TaskScheduler scheduler = proxy.getScheduler();

    private ProfileRepository profileRepository;

    private BungeeMain plugin;

    @EventHandler
    @SneakyThrows
    public void onPluginMessaging(PluginMessageEvent event) {


        scheduler.runAsync(plugin, () -> {
            if (!event.getTag().equals("nvcore:main")) return;
            val byteStream = ByteStreams.newDataInput(event.getData());
            val subChannel = byteStream.readUTF();
            if (!subChannel.equals("profile")) return;

            val name = byteStream.readUTF();

            val player = proxy.getPlayer(name);
            if (player != null) {
                val profile = profileRepository.getProfile(name);
                val data = ByteStreams.newDataOutput();
                data.writeUTF("profile");
                data.writeUTF(ProfileUtil.to(profile));
                player.getServer().sendData("nvcore:main", data.toByteArray());
            }
        });


    }

}
