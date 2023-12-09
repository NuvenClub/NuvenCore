package com.emanuelvini.nuven.core.bungee.command

import com.emanuelvini.nuven.core.bungee.configuration.LanguageValue
import com.emanuelvini.nuven.core.bungee.data.repository.ProfileRepository
import com.emanuelvini.nuven.core.bungee.listener.packet.PacketUtil
import com.emanuelvini.nuven.core.bungee.manager.RoleManager
import com.emanuelvini.nuven.core.shared.player.ranking.Role
import me.saiintbrisson.minecraft.command.annotation.Command
import me.saiintbrisson.minecraft.command.annotation.Optional
import me.saiintbrisson.minecraft.command.command.Context
import me.saiintbrisson.minecraft.command.target.CommandTarget
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.chat.ComponentSerializer


class TagCommand(
    private val roleManager: RoleManager,
    private val profileRepository: ProfileRepository
) {

    private val separator = TextComponent(", ")

    init {
        separator.color = ChatColor.WHITE
    }

    private fun generateByRole(role: Role, player: String): TextComponent {
        val component = TextComponent()
        component.text = role.name
        val preview = TextComponent(role.prefix + player)
        preview.addExtra(ComponentSerializer.parse("{text: \"\n\"}")[0])
        preview.addExtra(
            TextComponent("§eClique para selecionar")
        )
        component.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(preview).create())
        component.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tag apply ${role.order}")
        return component
    }

    @Command(name = "tag", target = CommandTarget.PLAYER)
    fun handleBase(
        context: Context<ProxiedPlayer>
    ) {
        val roles = roleManager.available(context.sender)
        val msg = TextComponent(LanguageValue[LanguageValue::tagList])
        msg.color = ChatColor.GREEN
        roles.forEachIndexed { index, it ->
            msg.addExtra(generateByRole(it, context.sender.name))
            if (index != roles.size-1) {
                msg.addExtra(separator)
            }
        }
        context.sender.sendMessage(msg)
    }

    @Command(name = "tag.apply", target = CommandTarget.PLAYER)
    fun handleApply(
        context: Context<ProxiedPlayer>,
        order: Int
    ) {
        val role = roleManager.roles[order]
        role?.let {
            if (context.sender.hasPermission(it.permission)) {
                val profile = profileRepository.getData(context.sender.name)
                profile.role = it
                PacketUtil.sendGetProfile(profile, context.sender)
                context.sendMessage(LanguageValue[LanguageValue::tagApplied]!!.replace("{tag}", role.name))
            }
        }
    }


}