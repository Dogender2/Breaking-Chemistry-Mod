package net.mcreator.brokenbad.client.gui;

import net.neoforged.neoforge.network.PacketDistributor;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;

import net.mcreator.brokenbad.world.inventory.GasLiquidContactorguiMenu;
import net.mcreator.brokenbad.procedures.HeatingMantleGUIWhileThisGUIIsOpenTickProcedure;
import net.mcreator.brokenbad.network.GasLiquidContactorguiButtonMessage;
import net.mcreator.brokenbad.init.BreakingChemistryModScreens;

import com.mojang.blaze3d.systems.RenderSystem;

public class GasLiquidContactorguiScreen extends AbstractContainerScreen<GasLiquidContactorguiMenu> implements BreakingChemistryModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_crystallize;
	private ImageButton imagebutton_tab_create_txt;
	private ImageButton imagebutton_tab_template_txt;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("breaking_chemistry:textures/screens/gas_liquid_contactorgui.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("breaking_chemistry:textures/screens/gas_lq_icon.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("breaking_chemistry:textures/screens/craft_arrow_icon.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("breaking_chemistry:textures/screens/plus_icon.png");

	public GasLiquidContactorguiScreen(GasLiquidContactorguiMenu container, Inventory inventory, Component text) {
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
		guiGraphics.blit(IMAGE_0, this.leftPos + -9, this.topPos + 17, 0, 0, 64, 64, 64, 64);
		guiGraphics.blit(IMAGE_1, this.leftPos + 99, this.topPos + 24, 0, 0, 32, 16, 32, 16);
		guiGraphics.blit(IMAGE_2, this.leftPos + 62, this.topPos + 24, 0, 0, 16, 16, 16, 16);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.breaking_chemistry.gas_liquid_contactorgui.label_gasliquid_contactor"), 7, 5, -12829636, false);
		guiGraphics.drawString(this.font, HeatingMantleGUIWhileThisGUIIsOpenTickProcedure.execute(world, x, y, z), 43, 68, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_crystallize = Button.builder(Component.translatable("gui.breaking_chemistry.gas_liquid_contactorgui.button_crystallize"), e -> {
			int x = GasLiquidContactorguiScreen.this.x;
			int y = GasLiquidContactorguiScreen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new GasLiquidContactorguiButtonMessage(0, x, y, z));
				GasLiquidContactorguiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 79, this.topPos + 44, 72, 20).build();
		this.addRenderableWidget(button_crystallize);
		imagebutton_tab_create_txt = new ImageButton(this.leftPos + 42, this.topPos + -32, 28, 32,
				new WidgetSprites(ResourceLocation.parse("breaking_chemistry:textures/screens/tab_create_txt.png"), ResourceLocation.parse("breaking_chemistry:textures/screens/tab_create_txt.png")), e -> {
					int x = GasLiquidContactorguiScreen.this.x;
					int y = GasLiquidContactorguiScreen.this.y;
					if (true) {
						PacketDistributor.sendToServer(new GasLiquidContactorguiButtonMessage(1, x, y, z));
						GasLiquidContactorguiButtonMessage.handleButtonAction(entity, 1, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_tab_create_txt);
		imagebutton_tab_template_txt = new ImageButton(this.leftPos + 14, this.topPos + -32, 28, 32,
				new WidgetSprites(ResourceLocation.parse("breaking_chemistry:textures/screens/tab_template_txt.png"), ResourceLocation.parse("breaking_chemistry:textures/screens/tab_template_txt.png")), e -> {
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_tab_template_txt);
	}
}