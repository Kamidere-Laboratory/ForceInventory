package re.kamide.forceinventory.config

import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Range

@Serializable
data class InventoryItem(
  override val item: String,
  override val name: String? = null,
  val slot: @Range(from = 1, to = 9) Int,
  val count: @Range(from = 0, to = 64) Int = 1,
  override val nbt: Boolean = false,
) : Item