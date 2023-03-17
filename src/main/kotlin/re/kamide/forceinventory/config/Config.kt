package re.kamide.forceinventory.config

import kotlinx.serialization.Serializable

@Serializable
data class Config(
  val inventory: List<InventoryRow>? = null,
  val equipment: Equipment? = null
)