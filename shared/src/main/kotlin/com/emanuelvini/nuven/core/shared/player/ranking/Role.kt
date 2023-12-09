package com.emanuelvini.nuven.core.shared.player.ranking


class Role(
    val name : String,
    val prefix: String,
    val permission: String,
    val order: Int,
    val alwaysInvisible : Boolean,
    val specialRole : Boolean,
    val joinBroadcast : Boolean,
    val coloredChat: Boolean
)
