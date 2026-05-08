package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.ModList;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.bus.api.IEventBus;

import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.brokenbad.jei_recipes.*;

@EventBusSubscriber
public class BreakingChemistryModRecipeTypes {
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, "breaking_chemistry");
	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, "breaking_chemistry");

	@SubscribeEvent
	public static void register(FMLConstructModEvent event) {
		IEventBus bus = ModList.get().getModContainerById("breaking_chemistry").get().getEventBus();
		event.enqueueWork(() -> {
			RECIPE_TYPES.register(bus);
			SERIALIZERS.register(bus);
			RECIPE_TYPES.register("heating_mantle_type_jei", () -> HeatingMantleTypeJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("heating_mantle_type_jei", () -> HeatingMantleTypeJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("magneticstirrer_jei_type", () -> MagneticstirrerJEITypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("magneticstirrer_jei_type", () -> MagneticstirrerJEITypeRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("laboratoryseparator_jei_type", () -> LaboratoryseparatorJEITypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("laboratoryseparator_jei_type", () -> LaboratoryseparatorJEITypeRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("gaslq_jei_type", () -> GaslqJEITypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("gaslq_jei_type", () -> GaslqJEITypeRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("gaslq_2_jei_type", () -> Gaslq2JEITypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("gaslq_2_jei_type", () -> Gaslq2JEITypeRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("dryer_jeitype", () -> DryerJEITYPERecipe.Type.INSTANCE);
			SERIALIZERS.register("dryer_jeitype", () -> DryerJEITYPERecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("box_vape_jei", () -> BoxVapeJEIRecipe.Type.INSTANCE);
			SERIALIZERS.register("box_vape_jei", () -> BoxVapeJEIRecipe.Serializer.INSTANCE);
			RECIPE_TYPES.register("hemp_pot_recipe_jei_type", () -> HempPotRecipeJeiTypeRecipe.Type.INSTANCE);
			SERIALIZERS.register("hemp_pot_recipe_jei_type", () -> HempPotRecipeJeiTypeRecipe.Serializer.INSTANCE);
		});
	}
}