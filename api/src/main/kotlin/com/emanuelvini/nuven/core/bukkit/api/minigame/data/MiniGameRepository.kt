package com.emanuelvini.nuven.core.bukkit.api.minigame.data

import com.henryfabio.sqlprovider.executor.SQLExecutor


class MiniGameRepository(
    private val executor: SQLExecutor,
    private val name: String
) {

}
