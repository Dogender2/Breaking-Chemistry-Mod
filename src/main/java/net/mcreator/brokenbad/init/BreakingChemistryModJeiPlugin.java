package net.mcreator.brokenbad.init;

import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import net.mcreator.brokenbad.jei_recipes.*;

import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.IModPlugin;

import java.util.stream.Collectors;
import java.util.Objects;
import java.util.List;

@JeiPlugin
public class BreakingChemistryModJeiPlugin implements IModPlugin {
	public static mezz.jei.api.recipe.RecipeType<HeatingMantleTypeJEIRecipe> HeatingMantleTypeJEI_Type = new mezz.jei.api.recipe.RecipeType<>(HeatingMantleTypeJEIRecipeCategory.UID, HeatingMantleTypeJEIRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<MagneticstirrerJEITypeRecipe> MagneticstirrerJEIType_Type = new mezz.jei.api.recipe.RecipeType<>(MagneticstirrerJEITypeRecipeCategory.UID, MagneticstirrerJEITypeRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<LaboratoryseparatorJEITypeRecipe> LaboratoryseparatorJEIType_Type = new mezz.jei.api.recipe.RecipeType<>(LaboratoryseparatorJEITypeRecipeCategory.UID, LaboratoryseparatorJEITypeRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<GaslqJEITypeRecipe> GaslqJEIType_Type = new mezz.jei.api.recipe.RecipeType<>(GaslqJEITypeRecipeCategory.UID, GaslqJEITypeRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<Gaslq2JEITypeRecipe> Gaslq2JEIType_Type = new mezz.jei.api.recipe.RecipeType<>(Gaslq2JEITypeRecipeCategory.UID, Gaslq2JEITypeRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<DryerJEITYPERecipe> DryerJEITYPE_Type = new mezz.jei.api.recipe.RecipeType<>(DryerJEITYPERecipeCategory.UID, DryerJEITYPERecipe.class);
	public static mezz.jei.api.recipe.RecipeType<BoxVapeJEIRecipe> BoxVapeJEI_Type = new mezz.jei.api.recipe.RecipeType<>(BoxVapeJEIRecipeCategory.UID, BoxVapeJEIRecipe.class);
	public static mezz.jei.api.recipe.RecipeType<HempPotRecipeJeiTypeRecipe> HempPotRecipeJeiType_Type = new mezz.jei.api.recipe.RecipeType<>(HempPotRecipeJeiTypeRecipeCategory.UID, HempPotRecipeJeiTypeRecipe.class);

	@Override
	public ResourceLocation getPluginUid() {
		return ResourceLocation.parse("breaking_chemistry:jei_plugin");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		registration.addRecipeCategories(new HeatingMantleTypeJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new MagneticstirrerJEITypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new LaboratoryseparatorJEITypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new GaslqJEITypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new Gaslq2JEITypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new DryerJEITYPERecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new BoxVapeJEIRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
		registration.addRecipeCategories(new HempPotRecipeJeiTypeRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		RecipeManager recipeManager = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
		List<HeatingMantleTypeJEIRecipe> HeatingMantleTypeJEIRecipes = recipeManager.getAllRecipesFor(HeatingMantleTypeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(HeatingMantleTypeJEI_Type, HeatingMantleTypeJEIRecipes);
		List<MagneticstirrerJEITypeRecipe> MagneticstirrerJEITypeRecipes = recipeManager.getAllRecipesFor(MagneticstirrerJEITypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(MagneticstirrerJEIType_Type, MagneticstirrerJEITypeRecipes);
		List<LaboratoryseparatorJEITypeRecipe> LaboratoryseparatorJEITypeRecipes = recipeManager.getAllRecipesFor(LaboratoryseparatorJEITypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(LaboratoryseparatorJEIType_Type, LaboratoryseparatorJEITypeRecipes);
		List<GaslqJEITypeRecipe> GaslqJEITypeRecipes = recipeManager.getAllRecipesFor(GaslqJEITypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(GaslqJEIType_Type, GaslqJEITypeRecipes);
		List<Gaslq2JEITypeRecipe> Gaslq2JEITypeRecipes = recipeManager.getAllRecipesFor(Gaslq2JEITypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(Gaslq2JEIType_Type, Gaslq2JEITypeRecipes);
		List<DryerJEITYPERecipe> DryerJEITYPERecipes = recipeManager.getAllRecipesFor(DryerJEITYPERecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(DryerJEITYPE_Type, DryerJEITYPERecipes);
		List<BoxVapeJEIRecipe> BoxVapeJEIRecipes = recipeManager.getAllRecipesFor(BoxVapeJEIRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(BoxVapeJEI_Type, BoxVapeJEIRecipes);
		List<HempPotRecipeJeiTypeRecipe> HempPotRecipeJeiTypeRecipes = recipeManager.getAllRecipesFor(HempPotRecipeJeiTypeRecipe.Type.INSTANCE).stream().map(RecipeHolder::value).collect(Collectors.toList());
		registration.addRecipes(HempPotRecipeJeiType_Type, HempPotRecipeJeiTypeRecipes);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(BreakingChemistryModBlocks.HEATING_MANTLE.get().asItem()), HeatingMantleTypeJEI_Type);
		registration.addRecipeCatalyst(new ItemStack(BreakingChemistryModBlocks.MAGNETICSTIRRER.get().asItem()), MagneticstirrerJEIType_Type);
		registration.addRecipeCatalyst(new ItemStack(BreakingChemistryModBlocks.LABORATORYSEPARATOR.get().asItem()), LaboratoryseparatorJEIType_Type);
		registration.addRecipeCatalyst(new ItemStack(BreakingChemistryModBlocks.GAS_LIQUID_CONTACTOR.get().asItem()), GaslqJEIType_Type);
		registration.addRecipeCatalyst(new ItemStack(BreakingChemistryModBlocks.GAS_LIQUID_CONTACTOR.get().asItem()), Gaslq2JEIType_Type);
		registration.addRecipeCatalyst(new ItemStack(BreakingChemistryModBlocks.DRYER.get().asItem()), DryerJEITYPE_Type);
		registration.addRecipeCatalyst(new ItemStack(BreakingChemistryModItems.BOX_VAPE_WITHOUT_COIL.get()), BoxVapeJEI_Type);
		registration.addRecipeCatalyst(new ItemStack(BreakingChemistryModBlocks.HEMP_POT.get().asItem()), HempPotRecipeJeiType_Type);
	}
}