package re.kamide.forceinventory.listeners

import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.EventHandler
import org.bukkit.inventory.ItemStack
import org.bukkit.Material
import net.kyori.adventure.text.minimessage.MiniMessage.miniMessage
import org.bukkit.Bukkit
import org.bukkit.event.EventPriority
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.PlayerInventory
import re.kamide.forceinventory.config.Config

internal class PlayerListener(private val config: Config) : Listener {
    private var forceInventory = Bukkit.createInventory(null, InventoryType.PLAYER)
    init {
        setItems()
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerJoin(event: PlayerJoinEvent) {

        val player = event.player
        player.inventory.clear()
        player.inventory.contents = forceInventory.contents
        // Sadly Bukkit.createInventory cannot create PlayerInventory so armor is iterated on join each time
        setEquipment(player.inventory)

        return
    }

    private fun setEquipment(playerInventory: PlayerInventory) {
        val (offhand, helmet, chestplate, leggings, boots) = config.getEquipment()
        if (offhand !== null) {
            val material = Material.matchMaterial(offhand.item)
            if (material === null) return
            val item = ItemStack(material, 1)
            if(offhand.name !== null) {
                val itemMeta = item.itemMeta
                itemMeta.displayName(miniMessage().deserialize(offhand.name))
                item.itemMeta = itemMeta
            }
            playerInventory.setItemInOffHand(item)
        }

        if (helmet !== null) {
            val material = Material.matchMaterial(helmet.item)
            if (material === null) return
            val item = ItemStack(material, 1)
            if(helmet.name !== null) {
                val itemMeta = item.itemMeta
                itemMeta.displayName(miniMessage().deserialize(helmet.name))
                item.itemMeta = itemMeta
            }
            playerInventory.helmet = item
        }

        if (chestplate !== null) {
            val material = Material.matchMaterial(chestplate.item)
            if (material === null) return
            val item = ItemStack(material, 1)
            if(chestplate.name !== null) {
                val itemMeta = item.itemMeta
                itemMeta.displayName(miniMessage().deserialize(chestplate.name))
            }
            playerInventory.chestplate = item
        }

        if (leggings !== null) {
            val material = Material.matchMaterial(leggings.item)
            if (material === null) return
            val item = ItemStack(material, 1)
            if(leggings.name !== null) {
                val itemMeta = item.itemMeta
                itemMeta.displayName(miniMessage().deserialize(leggings.name))
                item.itemMeta = itemMeta
            }
            playerInventory.leggings = item
        }

        if (boots !== null) {
            val material = Material.matchMaterial(boots.item)
            if (material === null) return
            val item = ItemStack(material, 1)
            if(boots.name !== null) {
                val itemMeta = item.itemMeta
                itemMeta.displayName(miniMessage().deserialize(boots.name))
                item.itemMeta = itemMeta
            }
            playerInventory.boots = item
        }
    }

    private fun setItems() {
        for (inventoryRow in config.getInventory()) {
            val row = when(inventoryRow.row) {
                1 -> 9
                2 -> 38
                3 -> 28
                4 -> 18
                else -> 99
            }

            for (item in inventoryRow.slots) {
                val material = Material.matchMaterial(item.item)

                if (material === null) continue

                val itemStack = ItemStack(material, item.count)

                if(item.name !== null) {
                    val itemMeta = itemStack.itemMeta
                    itemMeta.displayName(miniMessage().deserialize(item.name))
                    itemStack.itemMeta = itemMeta
                }

                forceInventory.setItem(row - 10 + item.slot, itemStack)
            }
        }
    }
}