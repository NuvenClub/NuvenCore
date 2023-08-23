package com.emanuelvini.nuven.core.bungee;


import com.emanuelvini.nuven.core.bungee.configuration.database.DatabaseValue;
import com.emanuelvini.nuven.core.bungee.data.repository.ProfileRepository;
import com.emanuelvini.nuven.core.bungee.logger.BungeeLogger;
import com.emanuelvini.nuven.core.bungee.manager.TagManager;
import com.emanuelvini.nuven.core.bungee.registry.ConfigurationRegistry;
import com.emanuelvini.nuven.core.bungee.registry.PacketListenerRegistry;
import com.henryfabio.sqlprovider.connector.type.impl.MySQLDatabaseType;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public class BungeeMain extends Plugin {

    @Getter
    @Accessors(fluent = true)
    private static BungeeMain instance;

    private BungeeLogger logger;

    private TagManager tagManager;

    private ProfileRepository profileRepository;


    @Override
    public void onLoad() {
        super.onLoad();
        logger = new BungeeLogger(ProxyServer.getInstance().getConsole());

    }

    @Override
    public void onEnable() {
        super.onEnable();

        val configurationRegistry = new ConfigurationRegistry(this);
        configurationRegistry.register();

        try {

            tagManager = new TagManager();

            val executor = new SQLExecutor(
                    MySQLDatabaseType
                            .builder()
                            .database(DatabaseValue.get(DatabaseValue::database))
                            .username(DatabaseValue.get(DatabaseValue::username))
                            .password(DatabaseValue.get(DatabaseValue::password))
                            .address(DatabaseValue.get(DatabaseValue::address))
                            .build()
                            .connect()
            );
            profileRepository = new ProfileRepository(executor, tagManager);

            logger.success("Conectado a database com sucesso.");

            getProxy().registerChannel("nvcore:main");
            logger.success("Canais de comunicações registrados com sucesso.");

            val packetListenerRegistry = new PacketListenerRegistry(this);
            packetListenerRegistry.register();



            logger.success("Plugin habilitado com sucesso.");
        } catch (Exception e) {
            logger.error("Falha ao acessar o banco de dados, por favor informe abaixo:");
            e.printStackTrace();
        }



    }
}
