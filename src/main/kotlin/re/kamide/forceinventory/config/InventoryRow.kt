package re.kamide.forceinventory.config

import javax.annotation.Nullable

@Nullable
data class InventoryRow(
  val row: Int,
  val slots: MutableList<InventoryItem>
)
