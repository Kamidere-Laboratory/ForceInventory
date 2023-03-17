package re.kamide.forceinventory.listeners

import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.EventHandler
import org.bukkit.inventory.ItemStack
import org.bukkit.Material
import net.kyori.adventure.text.minimessage.MiniMessage.miniMessage
import org.bukkit.Bukkit
import org.bukkit.event.EventPriority
import org.bukkit.event.inventory.InventoryType
import re.kamide.forceinventory.ForceInventory
import re.kamide.forceinventory.config.Config
import re.kamide.forceinventory.config.Item
import java.io.File

internal class PlayerListener(private val config: Config, private val plugin: ForceInventory) : Listener {
  private var forceInventory = Bukkit.createInventory(null, InventoryType.PLAYER)
  init {
    setItems()
    setEquipment()
  }
  @EventHandler(priority = EventPriority.HIGHEST)
  fun onPlayerJoin(event: PlayerJoinEvent) {

    val player = event.player
    val inventory = player.inventory
    inventory.clear()
    inventory.contents = forceInventory.contents

    return
  }

  private fun setEquipment() {
    if(config.equipment === null) return
    for ((place, itemData) in config.equipment.toMap().entries) {
      if(itemData === null) continue
      val item = parseItem(itemData)

      when(place){
        "offhand" -> forceInventory.setItem(40, item)
        "helmet" -> forceInventory.setItem(39, item)
        "chestplate" -> forceInventory.setItem(38, item)
        "leggings" -> forceInventory.setItem(37, item)
        "boots" -> forceInventory.setItem(36, item)
        else -> Unit
      }
    }
  }

  private fun setItems() {
    if(config.inventory === null) return
    for (inventoryRow in config.inventory) {

      for (itemData in inventoryRow.slots) {
        val item = parseItem(itemData)
        forceInventory.setItem(inventoryRow.lastSlotIndex - 10 + itemData.slot, item)
      }
    }
  }

  private fun parseItem(item: Item): ItemStack {
    if (item.nbt) {
      val filename = if (item.item.endsWith(".nbt")) item.item else "${item.item}.nbt"
      val itemFile = File(plugin.itemsDir , filename)
      if(!itemFile.exists()) throw Exception("NBT file not exists: $filename")
      return ItemStack.deserializeBytes(itemFile.readBytes())
    } else {
      val material = Material.matchMaterial(item.item)
      if (material === null) throw Exception("Can not find material: ${item.item}")
      val itemStack = ItemStack(material, 1)

      if(item.name !== null) {
        val itemMeta = itemStack.itemMeta
        itemMeta.displayName(miniMessage().deserialize(item.name !!))
        itemStack.itemMeta = itemMeta
      }
      return itemStack
    }
  }
}