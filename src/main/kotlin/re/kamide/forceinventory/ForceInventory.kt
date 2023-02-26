package re.kamide.forceinventory


import co.aikar.commands.PaperCommandManager
import com.charleskorn.kaml.Yaml
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.annotations.NotNull
import re.kamide.forceinventory.commands.MainCommand
import re.kamide.forceinventory.config.Config
import re.kamide.forceinventory.listeners.PlayerListener
import java.io.File

class ForceInventory: JavaPlugin() {
  private var commandManager: PaperCommandManager? = null
  private val configFile = File(dataFolder, "config.yml")
  @NotNull
  private var pluginConfig: Config? = null

  @Suppress("DEPRECATION")
  override fun onEnable() {
    commandManager = PaperCommandManager(this)
    commandManager!!.enableUnstableAPI("brigadier")
    commandManager!!.enableUnstableAPI("help")

    commandManager!!.registerCommand(MainCommand(this))
    updateConfig()
  }


  override fun onLoad() {
    saveDefaultConfig()
    super.onLoad()
  }

  fun updateConfig() {
    pluginConfig = Yaml.default.decodeFromStream(Config.serializer(), configFile.inputStream())
    registerEvents()
  }

  private fun registerEvents() {
    HandlerList.unregisterAll(this)
    server.pluginManager.registerEvents(PlayerListener(pluginConfig!!), this)
  }

  override fun onDisable() {
    commandManager?.unregisterCommands()
  }
}