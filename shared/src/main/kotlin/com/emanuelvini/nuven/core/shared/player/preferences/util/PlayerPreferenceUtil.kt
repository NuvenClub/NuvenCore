package com.emanuelvini.nuven.core.shared.player.preferences.util

import com.emanuelvini.nuven.core.shared.player.preferences.Preference
import com.emanuelvini.nuven.core.shared.player.preferences.PreferenceValue

object PlayerPreferenceUtil {

    fun from(str : String) : HashMap<Preference, Int> {
        val prefs = hashMapOf<Preference, Int>()
        str
            .split("!_!;")
            .forEach {
                val data = it.split("!:-!")
                val preference = PreferenceUtil.uniqueFrom(data[0])
                val value = data[1].toInt()
                prefs[preference] = value
            }
        return prefs
    }

    fun to(playerPrefs : HashMap<Preference, Int>) : String =
        playerPrefs
            .map { "${PreferenceUtil.uniqueTo(it.key)}!:-!${it.value}" }
            .joinToString { "!_!;" }


}