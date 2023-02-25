package re.kamide.forceinventory.config

import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Nullable

@Serializable
data class EquipmentItem(
  override val item: String,
  @get:Nullable
  override val name: String? = null,
) : Item