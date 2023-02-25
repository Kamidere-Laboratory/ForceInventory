package re.kamide.forceinventory


import com.charleskorn.kaml.Yaml
import org.bukkit.plugin.java.JavaPlugin
import re.kamide.forceinventory.config.Config
import re.kamide.forceinventory.listeners.PlayerListener
import java.io.File

class ForceInventory: JavaPlugin() {

  override fun onEnable() {
    val configFile = File(dataFolder, "config.yml").inputStream()
    val pluginConfig = Yaml.default.decodeFromStream(Config.serializer(), configFile)

    server.pluginManager.registerEvents(PlayerListener(pluginConfig), this)
  }


  override fun onLoad() {
    saveDefaultConfig()
    super.onLoad()
  }
}