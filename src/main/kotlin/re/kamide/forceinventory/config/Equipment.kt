package re.kamide.forceinventory.config

import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Nullable
import kotlin.reflect.full.declaredMemberProperties

@Serializable
data class Equipment(
  @get:Nullable
  val offhand: EquipmentItem? = null,
  @get:Nullable
  val helmet: EquipmentItem? = null,
  @get:Nullable
  val chestplate: EquipmentItem? = null,
  @get:Nullable
  val leggings: EquipmentItem? = null,
  @get:Nullable
  val boots: EquipmentItem? = null
) {
  public fun toMap() : Map<String, EquipmentItem?> {
    val map = mutableMapOf<String, EquipmentItem?>()
    map.set(key = "offhand", value = offhand)
    map.set(key = "helmet", value = helmet)
    map.set(key = "chestplate", value = chestplate)
    map.set(key = "leggings", value = leggings)
    map.set(key = "boots", value = boots)
    return map
//    ! For some reason it doesn't work but IDE show that everything is fine !
//    val props = Equipment::class.declaredMemberProperties.associateBy { it.name }
//    return props.keys.associateWith {
//      val value = props[it]?.get(this)
//      if (value is EquipmentItem) value
//      else null
//    }
  }
}