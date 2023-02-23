package re.kamide.forceinventory.config

data class InventoryItem(
  override val item: String,
  override val name: String?,
  val slot: Int,
  val count: Int,
) : Item