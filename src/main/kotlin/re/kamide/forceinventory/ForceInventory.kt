package re.kamide.forceinventory


import co.aikar.commands.PaperCommandManager
import com.charleskorn.kaml.Yaml
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin
import re.kamide.forceinventory.commands.MainCommand
import re.kamide.forceinventory.config.Config
import re.kamide.forceinventory.listeners.PlayerListener
import java.io.File

class ForceInventory: JavaPlugin() {
  private lateinit var commandManager: PaperCommandManager
  private lateinit var pluginConfig: Config
  lateinit var itemsDir: File

  @Suppress("DEPRECATION")
  override fun onEnable() {
    commandManager = PaperCommandManager(this)
    commandManager.enableUnstableAPI("brigadier")
    commandManager.enableUnstableAPI("help")

    commandManager.registerCommand(MainCommand(this))
    reloadConfig()
  }


  override fun onLoad() {
    saveDefaultConfig()
    itemsDir = File(dataFolder, "items")
    if(!itemsDir.exists()) {
      itemsDir.mkdirs()
    }
    super.onLoad()
  }

  override fun reloadConfig() {
    saveDefaultConfig()
    val configFile = File(dataFolder, "config.yml")
    pluginConfig = Yaml.default.decodeFromStream(Config.serializer(), configFile.inputStream())
    registerEvents()
  }

  private fun registerEvents() {
    HandlerList.unregisterAll(this)
    server.pluginManager.registerEvents(PlayerListener(pluginConfig, this), this)
  }

  override fun onDisable() {
    commandManager.unregisterCommands()
  }
}