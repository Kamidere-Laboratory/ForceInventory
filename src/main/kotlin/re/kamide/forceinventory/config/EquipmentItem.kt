package re.kamide.forceinventory.config

data class EquipmentItem(
  override val item: String,
  override val name: String?,
) : Item