package net.mcreator.brokenbad.jei_recipes;

import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.core.NonNullList;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import net.mcreator.brokenbad.init.BreakingChemistryModJeiPlugin;
import net.mcreator.brokenbad.init.BreakingChemistryModBlocks;

import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.constants.VanillaTypes;

import java.util.List;

public class LaboratoryseparatorJEITypeRecipeCategory implements IRecipeCategory<LaboratoryseparatorJEITypeRecipe> {
	public final static ResourceLocation UID = ResourceLocation.parse("breaking_chemistry:laboratoryseparator_jei_type");
	public final static ResourceLocation TEXTURE = ResourceLocation.parse("breaking_chemistry:textures/screens/jei_laboratory_sep.png");
	private final IDrawable background;
	private final IDrawable icon;
	private final Minecraft mc = Minecraft.getInstance();

	public LaboratoryseparatorJEITypeRecipeCategory(IGuiHelper helper) {
		this.background = helper.createDrawable(TEXTURE, 0, 0, 175, 81);
		this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(BreakingChemistryModBlocks.LABORATORYSEPARATOR.get().asItem()));
	}

	@Override
	public mezz.jei.api.recipe.RecipeType<LaboratoryseparatorJEITypeRecipe> getRecipeType() {
		return BreakingChemistryModJeiPlugin.LaboratoryseparatorJEIType_Type;
	}

	@Override
	public Component getTitle() {
		return Component.literal("Laboratory Separator");
	}

	@Override
	public IDrawable getIcon() {
		return this.icon;
	}

	@Override
	public int getWidth() {
		return this.background.getWidth();
	}

	@Override
	public int getHeight() {
		return this.background.getHeight();
	}

	@Override
	public void draw(LaboratoryseparatorJEITypeRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		this.background.draw(guiGraphics);

	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, LaboratoryseparatorJEITypeRecipe recipe, IFocusGroup focuses) {
		List<ItemStack> recipeOutputs = recipe.getResultItems();
		List<ItemStack> actualOutputs = NonNullList.withSize(2, ItemStack.EMPTY);
		for (int i = 0; i < recipeOutputs.size(); i++) {
			actualOutputs.set(i, recipeOutputs.get(i));
		}
		builder.addSlot(RecipeIngredientRole.INPUT, 28, 23).addIngredients(recipe.getIngredients().get(0));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 134, 23).addItemStack(actualOutputs.get(0));
		builder.addSlot(RecipeIngredientRole.INPUT, 74, 23).addIngredients(recipe.getIngredients().get(1));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 134, 60).addItemStack(actualOutputs.get(1));
	}
}