package re.kamide.forceinventory.config

import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.FileConfiguration

class Config(private val config: FileConfiguration) {
  fun getInventory(): List<InventoryRow> {
    val inventoryRows = config.getList("inventory", arrayListOf<Any>())
    val parsedInventoryRow = mutableListOf<InventoryRow>()
    if(inventoryRows !is ArrayList<*>) return arrayListOf()

    for (row in inventoryRows) {
      if(row !is LinkedHashMap<*, *>) continue

      val rowInt = row.get("row")
      val slots = row.get("slots")

      if (
        rowInt === null ||
        rowInt !is Int  ||
        slots === null  ||
        slots !is ArrayList<*>
      ) continue

      val inventoryRow = InventoryRow(row = rowInt, slots = mutableListOf())

      for (slot in slots) {
        if (slot !is LinkedHashMap<*, *>) continue
        val itemSlot = slot.get("slot")
        var itemName = slot.get("name")
        val itemId = slot.get("item")
        var itemCount = slot.get("count")

        if(itemSlot === null || itemId === null || itemSlot !is Int || itemId !is String) continue
        if(itemName !is String) itemName = null
        if(itemCount === null || itemCount !is Int) itemCount = 1

        val item = InventoryItem(
          slot = itemSlot,
          count = itemCount,
          item = itemId,
          name = itemName as String?
        )
        inventoryRow.slots.add(item)
      }
      parsedInventoryRow.add(inventoryRow)
    }
    return parsedInventoryRow.toList()
  }
  fun getEquipment(): Equipment {
    val equipmentSection = config.getConfigurationSection("equipment")
    return Equipment(
      offhand = getEquipmentItem(equipmentSection?.getConfigurationSection("offhand")),
      helmet = getEquipmentItem(equipmentSection?.getConfigurationSection("helmet")),
      chestplate = getEquipmentItem(equipmentSection?.getConfigurationSection("chestplate")),
      leggings = getEquipmentItem(equipmentSection?.getConfigurationSection("leggings")),
      boots = getEquipmentItem(equipmentSection?.getConfigurationSection("boots")),
    )
  }
  private fun getEquipmentItem(configurationSection: ConfigurationSection?): EquipmentItem? {
    if(configurationSection === null) return null
    return EquipmentItem(
      name = configurationSection.getString("name", null),
      item = configurationSection.getString("item", "minecraft:air")!!
    )
  }
}