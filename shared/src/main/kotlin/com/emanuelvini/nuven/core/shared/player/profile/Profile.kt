package com.emanuelvini.nuven.core.shared.player.profile

import com.emanuelvini.nuven.core.shared.player.ranking.Role


class Profile(
    val owner: String,
    val cash : Double,
    var role: Role
)