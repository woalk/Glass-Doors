package com.woalk.mods.glass_doors.blocks

import com.woalk.mods.glass_doors.GlassDoorsMod
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey

object GlassDoorCreativeTab {
    val key = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), GlassDoorsMod.id("tab"))
    val tab = FabricCreativeModeTab.builder()
        .icon { GlassDoors.paleOakDoor.asItem().defaultInstance }
        .title(Component.translatable("creativeTab.glass_doors"))
        .displayItems { _, output ->
            for (door in GlassDoors.doorBlocks) {
                output.accept(door.block)
            }
            for (trapdoor in GlassDoors.trapdoorBlocks) {
                output.accept(trapdoor.block)
            }
        }
        .build()

    internal fun registerAll() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab)
    }
}