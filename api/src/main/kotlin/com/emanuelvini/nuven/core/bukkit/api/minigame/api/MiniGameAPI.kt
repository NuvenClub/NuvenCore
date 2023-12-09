package com.emanuelvini.nuven.core.bukkit.api.minigame.api

import com.emanuelvini.nuven.core.bukkit.api.minigame.MiniGame
import com.emanuelvini.nuven.core.bukkit.api.minigame.manager.MiniGameManager

interface MiniGameAPI {
    fun getManager(name: String): com.emanuelvini.nuven.core.bukkit.api.minigame.manager.MiniGameManager?
    fun createAsMiniGame(name: String): com.emanuelvini.nuven.core.bukkit.api.minigame.MiniGame?
}
