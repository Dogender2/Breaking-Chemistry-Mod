package net.mcreator.brokenbad.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.brokenbad.world.inventory.LaboratorySeparatorguiMenu;
import net.mcreator.brokenbad.procedures.HeatingMantleGUIWhileThisGUIIsOpenTickProcedure;
import net.mcreator.brokenbad.network.LaboratorySeparatorguiButtonMessage;
import net.mcreator.brokenbad.init.BreakingChemistryModScreens;

import com.mojang.blaze3d.systems.RenderSystem;

public class LaboratorySeparatorguiScreen extends AbstractContainerScreen<LaboratorySeparatorguiMenu> implements BreakingChemistryModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_separate;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("breaking_chemistry:textures/screens/laboratory_separatorgui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("breaking_chemistry:textures/screens/separator_icon.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("breaking_chemistry:textures/screens/craft_arrow_icon.png");

	public LaboratorySeparatorguiScreen(LaboratorySeparatorguiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void updateMenuState(int elementType, String name, Object elementState) {
		menuStateUpdateActive = true;
		menuStateUpdateActive = false;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		guiGraphics.blit(BACKGROUND, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		guiGraphics.blit(IMAGE_0, this.leftPos + 12, this.topPos + 18, 0, 0, 48, 64, 48, 64);
		guiGraphics.blit(IMAGE_1, this.leftPos + 96, this.topPos + 23, 0, 0, 32, 16, 32, 16);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.breaking_chemistry.laboratory_separatorgui.label_laboratory_separator"), 6, 6, -12829636, false);
		guiGraphics.drawString(this.font, HeatingMantleGUIWhileThisGUIIsOpenTickProcedure.execute(world, x, y, z), 68, 43, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_separate = Button.builder(Component.translatable("gui.breaking_chemistry.laboratory_separatorgui.button_separate"), e -> {
			int x = LaboratorySeparatorguiScreen.this.x;
			int y = LaboratorySeparatorguiScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new LaboratorySeparatorguiButtonMessage(0, x, y, z));
				LaboratorySeparatorguiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 73, this.topPos + 58, 54, 20).build();
		this.addRenderableWidget(button_separate);
	}
}