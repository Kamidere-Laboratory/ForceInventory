package re.kamide.forceinventory.config

import kotlinx.serialization.Serializable

@Serializable
data class InventoryRow(
  val row: Int,
  val slots: MutableList<InventoryItem>
)
