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
import re.kamide.forceinventory.config.Config

internal class PlayerListener(private val config: Config) : Listener {
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
      val material = Material.matchMaterial(itemData.item)
      if (material === null) return
      val item = ItemStack(material, 1)
      if(itemData.name !== null) {
        val itemMeta = item.itemMeta
        itemMeta.displayName(miniMessage().deserialize(itemData.name))
        item.itemMeta = itemMeta
      }

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
      val row = when(inventoryRow.row) {
        1 -> 9
        2 -> 38
        3 -> 28
        4 -> 18
        else -> continue
      }

      for (item in inventoryRow.slots) {
        val material = Material.matchMaterial(item.item)

        if (material === null) continue

        val itemStack = ItemStack(material, item.count)

        if(item.name !== null) {
          val itemMeta = itemStack.itemMeta
          itemMeta.displayName(miniMessage().deserialize(item.name))
          itemStack.itemMeta = itemMeta
        }

        forceInventory.setItem(row - 10 + item.slot, itemStack)
      }
    }
  }
}