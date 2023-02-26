package re.kamide.forceinventory.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.CommandHelp
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.CommandPermission
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Description
import co.aikar.commands.annotation.HelpCommand
import co.aikar.commands.annotation.Subcommand
import net.kyori.adventure.text.minimessage.MiniMessage.miniMessage
import org.bukkit.command.CommandSender
import re.kamide.forceinventory.ForceInventory

@CommandAlias("forceinventory")
class MainCommand(private val plugin: ForceInventory) : BaseCommand() {

  @Subcommand("reload")
  @Description("Reload ForceInventory config")
  @CommandPermission("forceinventory.command.reload")
  fun onReloadSubCommand(sender: CommandSender) {
    plugin.updateConfig()
    sender.sendMessage(miniMessage().deserialize("ForceInventory config reloaded"))
  }

  @Default
  @HelpCommand
  fun onHelp(sender: CommandSender?, help: CommandHelp) {
    help.showHelp()
  }
}