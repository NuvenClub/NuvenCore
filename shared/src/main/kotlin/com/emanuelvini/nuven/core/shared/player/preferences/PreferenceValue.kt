package com.emanuelvini.nuven.core.shared.player.preferences


open class PreferenceValue<T : Enum<*>?>(
    private val preference: Preference,
    private val value: T
)