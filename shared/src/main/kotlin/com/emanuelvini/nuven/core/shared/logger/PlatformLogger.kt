package com.emanuelvini.nuven.core.shared.logger

interface PlatformLogger {
    fun log(vararg messages: String?)
    fun success(message: String) {
        log("§a$message")
    }

    fun error(message: String) {
        log("§c$message")
    }

    fun warn(message: String) {
        log("§e$message")
    }
}
