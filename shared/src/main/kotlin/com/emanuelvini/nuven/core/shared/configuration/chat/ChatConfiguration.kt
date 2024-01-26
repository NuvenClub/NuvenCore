package com.emanuelvini.nuven.core.shared.configuration.chat

class ChatConfiguration (
    val delay : Double,
    val messageReplace : HashMap<String, String>,
    val minLength : Int,
    val capslockPercentage : Int,
    val capslockSkipWords : List<String>,
    val allowedDomains : List<String>,
    val punishCommand : String,
    val blockedContent : List<String>
)