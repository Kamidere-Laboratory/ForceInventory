package re.kamide.forceinventory

import org.bukkit.plugin.java.JavaPlugin
import re.kamide.forceinventory.config.Config
import re.kamide.forceinventory.listeners.PlayerListener

class ForceInventory: JavaPlugin() {

  override fun onEnable() {
    val pluginConfig = Config(config)

    server.pluginManager.registerEvents(PlayerListener(pluginConfig), this)
  }

  override fun onLoad() {
    saveDefaultConfig()
    super.onLoad()
  }
}