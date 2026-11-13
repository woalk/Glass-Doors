package com.woalk.mods.glass_doors.blocks

import com.woalk.mods.glass_doors.GlassDoorsMod
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.references.BlockItemId
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.DoubleHighBlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.TrapDoorBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.BlockSetType

object GlassDoors {
    data class PreWrapper<T : Block>(
        val vanillaBlock: T,
        val blockGenerator: (BlockBehaviour.Properties) -> T,
        val properties: BlockBehaviour.Properties,
        val id: String,
        val itemGenerator: (Block, Item.Properties) -> BlockItem = ::BlockItem,
        val itemProperties: Item.Properties = Item.Properties(),
    )
    data class Wrapper<T: Block>(
        val block: T,
        val vanillaBlock: T,
    ) {
        val item
            get() = block.asItem()

        val vanillaItem
            get() = vanillaBlock.asItem()
    }

    val doorBlocks = ArrayList<Wrapper<DoorBlock>>(VANILLA_DOORS.size)
    val trapdoorBlocks = ArrayList<Wrapper<TrapDoorBlock>>(VANILLA_TRAPDOORS.size)

    lateinit var paleOakDoor: Block

    init {
        val doors = ArrayList<PreWrapper<DoorBlock>>(VANILLA_DOORS.size)
        val trapdoors = ArrayList<PreWrapper<TrapDoorBlock>>(VANILLA_TRAPDOORS.size)

        for (vanillaDoor in VANILLA_DOORS) {
            val properties = BlockBehaviour.Properties.ofFullCopy(vanillaDoor)
            val glassDoor = { p: BlockBehaviour.Properties -> DoorBlock(vanillaDoor.type(), p) }
            val vanillaId = BuiltInRegistries.BLOCK.getKey(vanillaDoor).path
            val itemGenerator = ::DoubleHighBlockItem
            doors.add(PreWrapper(vanillaDoor, glassDoor, properties, insertGlassIntoId(vanillaId), itemGenerator))
        }
        for (vanillaTrapdoor in VANILLA_TRAPDOORS) {
            val properties = BlockBehaviour.Properties.ofFullCopy(vanillaTrapdoor)
            val glassTrapdoor = { p: BlockBehaviour.Properties -> TrapDoorBlock(vanillaTrapdoor.type, p) }
            val vanillaId = BuiltInRegistries.BLOCK.getKey(vanillaTrapdoor).path
            trapdoors.add(PreWrapper(vanillaTrapdoor, glassTrapdoor, properties, insertGlassIntoId(vanillaId)))
        }

        for (w in doors) {
            val doorBlock = register(w)
            doorBlocks.add(doorBlock)

            if (w.vanillaBlock.type() == BlockSetType.PALE_OAK) {
                paleOakDoor = doorBlock.block
            }
        }
        for (w in trapdoors) {
            trapdoorBlocks.add(register(w))
        }
    }

    private fun insertGlassIntoId(vanillaId: String): String = Regex("(_(trap)?door)$").replace(vanillaId) {
        "_glass" + it.groups.first()?.value
    }

    private fun <T: Block> register(w: PreWrapper<T>): Wrapper<T> {
        val id = GlassDoorsMod.id(w.id)
        val blockItemId = BlockItemId(
            ResourceKey.create(Registries.BLOCK, id),
            ResourceKey.create(Registries.ITEM, id),
        )
        val block = w.blockGenerator(w.properties.setId(blockItemId.block))
        Registry.register(BuiltInRegistries.BLOCK, blockItemId.block, block)

        val blockItem = w.itemGenerator(block, w.itemProperties.useBlockDescriptionPrefix().setId(blockItemId.item))
        Registry.register(BuiltInRegistries.ITEM, blockItemId.item, blockItem)

        return Wrapper(block, w.vanillaBlock)
    }

    internal fun registerAll() {
        GlassDoorsMod.LOGGER.debug("Registered ${doorBlocks.size} doors and ${trapdoorBlocks.size} trapdoors.")
    }
}