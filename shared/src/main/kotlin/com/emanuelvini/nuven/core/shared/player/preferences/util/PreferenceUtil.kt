package com.emanuelvini.nuven.core.shared.player.preferences.util

import com.emanuelvini.nuven.core.shared.player.preferences.Preference
import com.emanuelvini.nuven.core.shared.player.preferences.PreferenceValueMappedType

object PreferenceUtil {

    fun from(enc : String) : List<Preference> {
        val prefs = mutableListOf<Preference>()
        enc.split(":/!").forEach {
            val prefData = it.split("!!")
            val name = prefData[0]
            val id = prefData[1]
            val defaultValue = prefData[2].toInt()
            val typeValue = PreferenceValueMappedType.entries[prefData[3].toInt()]
            prefs.add(
                object : Preference(
                    name, id, defaultValue, typeValue
                ) {}
            )
        }
        return prefs
    }

    fun uniqueFrom(enc : String) : Preference {
        val prefData = enc.split("!!")
        val name = prefData[0]
        val id = prefData[1]
        val defaultValue = prefData[2].toInt()
        val typeValue = PreferenceValueMappedType.entries[prefData[3].toInt()]
        return object : Preference(
            name, id, defaultValue, typeValue
        ) {}
    }


    fun uniqueTo(pref : Preference) : String = "${pref.name}!!${pref.id}!!${pref.defaultValue}!!${pref.valueType.ordinal}"

    fun to(prefs : List<Preference>) : String {
        val pr = mutableListOf<String>()
        prefs.forEach {
            pr.add(
                "${it.name}!!${it.id}!!${it.defaultValue}!!${it.valueType.ordinal}"
            )
        }
        return pr.joinToString(":/!")
    }
}