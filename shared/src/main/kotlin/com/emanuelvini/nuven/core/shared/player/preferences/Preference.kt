package com.emanuelvini.nuven.core.shared.player.preferences


abstract class Preference (
    val name: String,
    val id: String,
    val defaultValue : Int,
    val valueType : PreferenceValueMappedType
)
