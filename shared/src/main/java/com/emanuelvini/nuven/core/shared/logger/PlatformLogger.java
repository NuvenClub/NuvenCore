package com.emanuelvini.nuven.core.shared.logger;

public interface PlatformLogger {

    void log(String... messages);

    default void success(String message) {
        log("§a" + message);
    }
    default void error(String message) {
        log("§c" + message);
    }
    default void warn(String message) {
        log("§e" + message);
    }

}
