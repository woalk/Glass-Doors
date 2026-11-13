package com.woalk.mods.glass_doors.client.datagen.data

import com.woalk.mods.glass_doors.blocks.GlassDoors
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancements.Advancement
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.RecipeProvider.getHasName
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Recipe
import java.util.concurrent.CompletableFuture

class GlassDoorRecipeProvider(
    output: FabricPackOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
) : FabricRecipeProvider(output, registriesFuture) {

    override fun createRecipeProvider(
        registries: HolderLookup.Provider,
        recipes: BootstrapContext<Recipe<*>>,
        advancements: BootstrapContext<Advancement>
    ): RecipeProvider = object : RecipeProvider(recipes, advancements) {
        override fun buildRecipes() {
            for (door in GlassDoors.doorBlocks) {
                glassPaneRecipe(door.item, door.vanillaItem).save(output)
            }
            for (trapdoor in GlassDoors.trapdoorBlocks) {
                glassPaneRecipe(trapdoor.item, trapdoor.vanillaItem).save(output)
            }
        }

        private fun glassPaneRecipe(item: Item, vanillaItem: Item): ShapelessRecipeBuilder =
            shapeless(RecipeCategory.REDSTONE, item)
                .requires(vanillaItem)
                .requires(Items.GLASS_PANE)
                .unlockedBy(getHasName(vanillaItem), has(vanillaItem))
    }
}