package com.woalk.mods.glass_doors

import com.woalk.mods.glass_doors.blocks.GlassDoorCreativeTab
import com.woalk.mods.glass_doors.blocks.GlassDoors
import net.fabricmc.api.ModInitializer
import net.minecraft.resources.Identifier
import org.slf4j.LoggerFactory

object GlassDoorsMod : ModInitializer {
	const val MOD_ID: String = "glass_doors"
	val LOGGER = LoggerFactory.getLogger(MOD_ID)!!

	override fun onInitialize() {
		GlassDoors.registerAll()
		GlassDoorCreativeTab.registerAll()
	}

	fun id(path: String) = Identifier.fromNamespaceAndPath(MOD_ID, path)
}
