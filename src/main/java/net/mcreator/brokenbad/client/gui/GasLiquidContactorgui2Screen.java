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

import net.mcreator.brokenbad.world.inventory.GasLiquidContactorgui2Menu;
import net.mcreator.brokenbad.procedures.HeatingMantleGUIWhileThisGUIIsOpenTickProcedure;
import net.mcreator.brokenbad.network.GasLiquidContactorgui2ButtonMessage;
import net.mcreator.brokenbad.init.BreakingChemistryModScreens;

import com.mojang.blaze3d.systems.RenderSystem;

public class GasLiquidContactorgui2Screen extends AbstractContainerScreen<GasLiquidContactorgui2Menu> implements BreakingChemistryModScreens.ScreenAccessor {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	private boolean menuStateUpdateActive = false;
	private Button button_create;
	private ImageButton imagebutton_tab_create_txt;
	private ImageButton imagebutton_tab_template_txt;
	private static final ResourceLocation BACKGROUND = ResourceLocation.parse("breaking_chemistry:textures/screens/gas_liquid_contactorgui_2.png");
	private static final ResourceLocation IMAGE_0 = ResourceLocation.parse("breaking_chemistry:textures/screens/gas_lq_icon.png");
	private static final ResourceLocation IMAGE_1 = ResourceLocation.parse("breaking_chemistry:textures/screens/craft_arrow_icon.png");
	private static final ResourceLocation IMAGE_2 = ResourceLocation.parse("breaking_chemistry:textures/screens/plus_icon.png");

	public GasLiquidContactorgui2Screen(GasLiquidContactorgui2Menu container, Inventory inventory, Component text) {
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
		guiGraphics.blit(IMAGE_1, this.leftPos + 99, this.topPos + 19, 0, 0, 32, 16, 32, 16);
		guiGraphics.blit(IMAGE_2, this.leftPos + 62, this.topPos + 19, 0, 0, 16, 16, 16, 16);
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
		guiGraphics.drawString(this.font, Component.translatable("gui.breaking_chemistry.gas_liquid_contactorgui_2.label_gasliquid_contactor"), 7, 5, -12829636, false);
		guiGraphics.drawString(this.font, HeatingMantleGUIWhileThisGUIIsOpenTickProcedure.execute(world, x, y, z), 43, 67, -12829636, false);
	}

	@Override
	public void init() {
		super.init();
		button_create = Button.builder(Component.translatable("gui.breaking_chemistry.gas_liquid_contactorgui_2.button_create"), e -> {
			int x = GasLiquidContactorgui2Screen.this.x;
			int y = GasLiquidContactorgui2Screen.this.y;
			if (true) {
				PacketDistributor.sendToServer(new GasLiquidContactorgui2ButtonMessage(0, x, y, z));
				GasLiquidContactorgui2ButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 88, this.topPos + 42, 54, 20).build();
		this.addRenderableWidget(button_create);
		imagebutton_tab_create_txt = new ImageButton(this.leftPos + 42, this.topPos + -32, 28, 32,
				new WidgetSprites(ResourceLocation.parse("breaking_chemistry:textures/screens/tab_create_txt.png"), ResourceLocation.parse("breaking_chemistry:textures/screens/tab_create_txt.png")), e -> {
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_tab_create_txt);
		imagebutton_tab_template_txt = new ImageButton(this.leftPos + 14, this.topPos + -32, 28, 32,
				new WidgetSprites(ResourceLocation.parse("breaking_chemistry:textures/screens/tab_template_txt.png"), ResourceLocation.parse("breaking_chemistry:textures/screens/tab_template_txt.png")), e -> {
					int x = GasLiquidContactorgui2Screen.this.x;
					int y = GasLiquidContactorgui2Screen.this.y;
					if (true) {
						PacketDistributor.sendToServer(new GasLiquidContactorgui2ButtonMessage(2, x, y, z));
						GasLiquidContactorgui2ButtonMessage.handleButtonAction(entity, 2, x, y, z);
					}
				}) {
			@Override
			public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
				guiGraphics.blit(sprites.get(isActive(), isHoveredOrFocused()), getX(), getY(), 0, 0, width, height, width, height);
			}
		};
		this.addRenderableWidget(imagebutton_tab_template_txt);
	}
}