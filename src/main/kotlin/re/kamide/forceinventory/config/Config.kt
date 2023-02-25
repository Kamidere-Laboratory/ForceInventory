package re.kamide.forceinventory.config

import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Nullable

@Serializable
data class Config(
  @get:Nullable
  val inventory: List<InventoryRow>? = null,
  @get:Nullable
  val equipment: Equipment? = null
)