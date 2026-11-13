package com.woalk.mods.glass_doors.client.datagen.data

import com.woalk.mods.glass_doors.blocks.GlassDoors
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.BlockTags
import net.minecraft.world.level.block.state.properties.BlockSetType
import java.util.concurrent.CompletableFuture

class GlassDoorBlockTagProvider(
    output: FabricPackOutput,
    registryLookupFuture: CompletableFuture<HolderLookup.Provider>
) : FabricTagsProvider.BlockTagsProvider(output, registryLookupFuture) {
    override fun addTags(registries: HolderLookup.Provider) {
        val axe = builder(BlockTags.MINEABLE_WITH_AXE).setReplace(false)
        val pickaxe = builder(BlockTags.MINEABLE_WITH_PICKAXE).setReplace(false)

        for (door in GlassDoors.doorBlocks) {
            if (door.block.type() == BlockSetType.IRON) {
                pickaxe.add(door.block.properties().blockIdOrThrow())
            } else {
                axe.add(door.block.properties().blockIdOrThrow())
            }
        }
        for (trapdoor in GlassDoors.trapdoorBlocks) {
            if (trapdoor.block.type == BlockSetType.IRON) {
                pickaxe.add(trapdoor.block.properties().blockIdOrThrow())
            } else {
                axe.add(trapdoor.block.properties().blockIdOrThrow())
            }
        }
    }
}