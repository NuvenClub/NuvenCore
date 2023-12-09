package com.emanuelvini.nuven.core.bukkit.api.minigame

import com.emanuelvini.nuven.core.bukkit.api.minigame.profile.MiniGameProfile

interface MiniGame {

    val name: String

    fun getProfile(name: String): com.emanuelvini.nuven.core.bukkit.api.minigame.profile.MiniGameProfile?
}
