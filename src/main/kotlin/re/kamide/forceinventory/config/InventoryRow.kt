package re.kamide.forceinventory.config

import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Range

@Serializable
data class InventoryRow(
  val row: @Range(from = 1, to = 4) Int,
  val slots: MutableList<InventoryItem>
) {
  private val computeLastSlotIndex: Int
    get() {
      return when(row) {
        1 -> 9
        2 -> 38
        3 -> 28
        4 -> 18
        else -> throw Exception("Row out of range, Expected range: 1-4")
      }
    }

  public val lastSlotIndex: Int = computeLastSlotIndex
}
