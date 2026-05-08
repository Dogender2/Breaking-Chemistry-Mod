/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.core.registries.Registries;
import net.minecraft.client.Minecraft;

import net.mcreator.brokenbad.world.inventory.*;
import net.mcreator.brokenbad.network.MenuStateUpdateMessage;
import net.mcreator.brokenbad.BreakingChemistryMod;

import java.util.Map;

public class BreakingChemistryModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(Registries.MENU, BreakingChemistryMod.MODID);
	public static final DeferredHolder<MenuType<?>, MenuType<HeatingMantleGUIMenu>> HEATING_MANTLE_GUI = REGISTRY.register("heating_mantle_gui", () -> IMenuTypeExtension.create(HeatingMantleGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<MagneticStirrerGUIMenu>> MAGNETIC_STIRRER_GUI = REGISTRY.register("magnetic_stirrer_gui", () -> IMenuTypeExtension.create(MagneticStirrerGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<LaboratorySeparatorguiMenu>> LABORATORY_SEPARATORGUI = REGISTRY.register("laboratory_separatorgui", () -> IMenuTypeExtension.create(LaboratorySeparatorguiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<GasLiquidContactorguiMenu>> GAS_LIQUID_CONTACTORGUI = REGISTRY.register("gas_liquid_contactorgui", () -> IMenuTypeExtension.create(GasLiquidContactorguiMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<GasLiquidContactorgui2Menu>> GAS_LIQUID_CONTACTORGUI_2 = REGISTRY.register("gas_liquid_contactorgui_2", () -> IMenuTypeExtension.create(GasLiquidContactorgui2Menu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<DryerGUIMenu>> DRYER_GUI = REGISTRY.register("dryer_gui", () -> IMenuTypeExtension.create(DryerGUIMenu::new));
	public static final DeferredHolder<MenuType<?>, MenuType<BoxVapeGUIMenu>> BOX_VAPE_GUI = REGISTRY.register("box_vape_gui", () -> IMenuTypeExtension.create(BoxVapeGUIMenu::new));

	public interface MenuAccessor {
		Map<String, Object> getMenuState();

		Map<Integer, Slot> getSlots();

		default void sendMenuStateUpdate(Player player, int elementType, String name, Object elementState, boolean needClientUpdate) {
			getMenuState().put(elementType + ":" + name, elementState);
			if (player instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new MenuStateUpdateMessage(elementType, name, elementState));
			} else if (player.level().isClientSide) {
				if (Minecraft.getInstance().screen instanceof BreakingChemistryModScreens.ScreenAccessor accessor && needClientUpdate)
					accessor.updateMenuState(elementType, name, elementState);
				PacketDistributor.sendToServer(new MenuStateUpdateMessage(elementType, name, elementState));
			}
		}

		default <T> T getMenuState(int elementType, String name, T defaultValue) {
			try {
				return (T) getMenuState().getOrDefault(elementType + ":" + name, defaultValue);
			} catch (ClassCastException e) {
				return defaultValue;
			}
		}
	}
}