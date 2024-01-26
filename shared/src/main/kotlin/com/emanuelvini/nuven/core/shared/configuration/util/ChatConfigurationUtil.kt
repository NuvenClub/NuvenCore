package com.emanuelvini.nuven.core.shared.configuration.util

import com.emanuelvini.nuven.core.shared.configuration.chat.ChatConfiguration

object ChatConfigurationUtil {

    fun from(str : String) : ChatConfiguration {
        val props = str.split("/12/")
        val delay = props[0].toDouble()
        val messageReplaces = HashMap<String, String>()
        props[1].split(";").forEach {
            val localProps = it.split("->")

            val fr = localProps[0]
            val to = localProps[1]
            messageReplaces[fr] = to

        }
        val minLength = props[2].toInt()
        val capslockPercentage = props[3].toInt()
        val capslockSkipWords = props[4].split("../")
        return ChatConfiguration(
            delay,
            messageReplaces,
            minLength,
            capslockPercentage,
            capslockSkipWords,
            props[5].split("../"),
            props[7],
            props[6].split("../")
        )
    }

    fun to(chatConfiguration: ChatConfiguration) : String {
        val messageReplaces = mutableListOf<String>()
        chatConfiguration.messageReplace.forEach {
            messageReplaces.add(
                "${it.key}->${it.value}"
            )
        }
        return "${chatConfiguration.delay}/12/" +
                "${messageReplaces.joinToString (";")}/12/" +
                "${chatConfiguration.minLength}/12/" +
                "${chatConfiguration.capslockPercentage}/12/" +
                "${chatConfiguration.capslockSkipWords.joinToString("../")}/12/" +
                "${chatConfiguration.allowedDomains.joinToString ("../")}/12/" +
                "${chatConfiguration.blockedContent.joinToString("../")}/12/" +
                chatConfiguration.punishCommand
    }

    fun toHashMapChatConfiguration(messageReplaces : List<String>) : HashMap<String, String> {
        val hash = HashMap<String, String>()
        messageReplaces.forEach { s ->
            val props = s.split("->")
            val from = props[0].split(",")
            val to = props[1]
            from.forEach {
                hash[it] = to
            }
        }
        return hash
    }
}