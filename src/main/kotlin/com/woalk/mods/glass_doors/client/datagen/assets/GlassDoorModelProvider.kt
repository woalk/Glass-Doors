package com.woalk.mods.glass_doors.client.datagen.assets

import com.woalk.mods.glass_doors.blocks.GlassDoors
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.minecraft.client.data.models.BlockModelGenerators
import net.minecraft.client.data.models.ItemModelGenerators
import net.minecraft.client.data.models.model.ModelTemplates

class GlassDoorModelProvider(output: FabricPackOutput) : FabricModelProvider(output) {
    override fun generateBlockStateModels(blockModelGenerators: BlockModelGenerators) {
        for (door in GlassDoors.doorBlocks) {
            blockModelGenerators.createDoor(door.block)
        }
        for (trapdoor in GlassDoors.trapdoorBlocks) {
            blockModelGenerators.createTrapdoor(trapdoor.block)
        }
    }

    override fun generateItemModels(itemModelGenerators: ItemModelGenerators) {
        // item models are registered by `createDoor` and `createTrapdoor` automatically
    }
}