package com.woalk.mods.glass_doors.client.datagen

import com.woalk.mods.glass_doors.client.datagen.assets.GlassDoorModelProvider
import com.woalk.mods.glass_doors.client.datagen.data.GlassDoorBlockTagProvider
import com.woalk.mods.glass_doors.client.datagen.data.GlassDoorLootTableProvider
import com.woalk.mods.glass_doors.client.datagen.data.GlassDoorRecipeProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object GlassDoorsDataGenerator : DataGeneratorEntrypoint {
	override fun onInitializeDataGenerator(fabricDataGenerator: FabricDataGenerator) {
		val pack = fabricDataGenerator.createPack()
		pack.addProvider(::GlassDoorModelProvider)
		pack.addProvider(::GlassDoorLootTableProvider)
		pack.addProvider(::GlassDoorRecipeProvider)
		pack.addProvider(::GlassDoorBlockTagProvider)
	}
}