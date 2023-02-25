package re.kamide.forceinventory.config

import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Nullable
@Serializable
data class InventoryItem(
  override val item: String,
  @get:Nullable
  override val name: String? = null,
  val slot: Int,
  val count: Int = 1,
) : Item