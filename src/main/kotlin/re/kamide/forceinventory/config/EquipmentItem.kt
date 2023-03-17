package re.kamide.forceinventory.config

import kotlinx.serialization.Serializable

@Serializable
data class EquipmentItem(
  override val item: String,
  override val name: String? = null,
  override val nbt: Boolean = false
) : Item