package re.kamide.forceinventory.config

import kotlinx.serialization.Serializable
import kotlin.reflect.full.declaredMemberProperties

@Serializable
data class Equipment(
  val offhand: EquipmentItem? = null,
  val helmet: EquipmentItem? = null,
  val chestplate: EquipmentItem? = null,
  val leggings: EquipmentItem? = null,
  val boots: EquipmentItem? = null
) {
  fun toMap() : Map<String, EquipmentItem?> {
    try {
      val props = Equipment::class.declaredMemberProperties.associateBy { it.name }
      return props.keys.associateWith {
        val value = props[it]?.get(this)
        if (value is EquipmentItem) value
        else null
      }
    } catch (_: Throwable) {
      val map = mutableMapOf<String, EquipmentItem?>()
      map.set(key = "offhand", value = offhand)
      map.set(key = "helmet", value = helmet)
      map.set(key = "chestplate", value = chestplate)
      map.set(key = "leggings", value = leggings)
      map.set(key = "boots", value = boots)
      return map
    }
  }
}