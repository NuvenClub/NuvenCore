package com.emanuelvini.nuven.core.bungee.logger;


import com.emanuelvini.nuven.core.shared.logger.PlatformLogger;
import lombok.AllArgsConstructor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

@AllArgsConstructor
public class BungeeLogger implements PlatformLogger {

    private CommandSender console;

    @Override
    public void log(String... messages) {
        console.sendMessage(TextComponent.fromLegacyText("§b[NuvenCore] §e[BUNGEE]: §f" + String.join(" ", messages)));
    }

}
