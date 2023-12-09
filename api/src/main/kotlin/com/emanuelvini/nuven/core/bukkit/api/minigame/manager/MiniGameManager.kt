package com.emanuelvini.nuven.core.bukkit.api.minigame.manager

import com.emanuelvini.nuven.core.bukkit.api.minigame.api.npc.model.NPCModel
import com.emanuelvini.nuven.core.bukkit.api.minigame.data.MiniGameRepository

interface MiniGameManager {
    val repository: com.emanuelvini.nuven.core.bukkit.api.minigame.data.MiniGameRepository
    fun createNPC(model: com.emanuelvini.nuven.core.bukkit.api.minigame.api.npc.model.NPCModel)
    val npcs: List<com.emanuelvini.nuven.core.bukkit.api.minigame.api.npc.model.NPCModel>
}
