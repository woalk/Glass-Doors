package com.woalk.mods.glass_doors.client.datagen.data

import com.woalk.mods.glass_doors.blocks.GlassDoors
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class GlassDoorLootTableProvider(
    packOutput: FabricPackOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>
) : FabricBlockLootSubProvider(packOutput, registriesFuture) {

    override fun generate() {
        for (door in GlassDoors.doorBlocks) {
            add(door.block, createDoorTable(door.block))
        }
        for (trapdoor in GlassDoors.trapdoorBlocks) {
            dropSelf(trapdoor.block)
        }
    }
}