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

import net.mcreator.brokenbad.world.inventory.DryerGUIMenu;
import net.mcreator.brokenbad.procedures.HeatingMantleGUIWhileThisGUIIsOpenTickProcedure;
import net.mcreator.brokenbad.network.DryerGUIButtonMessage;
import net.mcreator.brokenbad.init.BreakingChemistryModScreens;

import com.mojang.blaze3d.systems.RenderSystem;

public class DryerGUIScreen extends AbstractContainerScreen<DryerGUIMenu> implements BreakingChemistryModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_dry;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("breaking_chemistry:textures/screens/dryer_gui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("breaking_chemistry:textures/screens/dryer_icon.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("breaking_chemistry:textures/screens/craft_arrow_icon.png");

	public DryerGUIScreen(DryerGUIMenu container, Inventory inventory, Component text) {
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
		guiGraphics.blit(IMAGE_0, this.leftPos + 112, this.topPos + 19, 0, 0, 48, 48, 48, 48);
		guiGraphics.blit(IMAGE_1, this.leftPos + 54, this.topPos + 25, 0, 0, 32, 16, 32, 16);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.breaking_chemistry.dryer_gui.label_dryer"), 8, 8, -12829636, false);
		guiGraphics.drawString(this.font, HeatingMantleGUIWhileThisGUIIsOpenTickProcedure.execute(world, x, y, z), 43, 9, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_dry = Button.builder(Component.translatable("gui.breaking_chemistry.dryer_gui.button_dry"), e -> {
			int x = DryerGUIScreen.this.x;
			int y = DryerGUIScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new DryerGUIButtonMessage(0, x, y, z));
				DryerGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 55, this.topPos + 52, 30, 20).build();
		this.addRenderableWidget(button_dry);
	}
}