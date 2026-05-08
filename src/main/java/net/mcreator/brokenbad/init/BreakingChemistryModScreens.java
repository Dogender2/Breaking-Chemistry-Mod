/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.brokenbad.client.gui.*;

@EventBusSubscriber(Dist.CLIENT)
public class BreakingChemistryModScreens {
	@SubscribeEvent
	public static void clientLoad(RegisterMenuScreensEvent event) {
		event.register(BreakingChemistryModMenus.HEATING_MANTLE_GUI.get(), HeatingMantleGUIScreen::new);
		event.register(BreakingChemistryModMenus.MAGNETIC_STIRRER_GUI.get(), MagneticStirrerGUIScreen::new);
		event.register(BreakingChemistryModMenus.LABORATORY_SEPARATORGUI.get(), LaboratorySeparatorguiScreen::new);
		event.register(BreakingChemistryModMenus.GAS_LIQUID_CONTACTORGUI.get(), GasLiquidContactorguiScreen::new);
		event.register(BreakingChemistryModMenus.GAS_LIQUID_CONTACTORGUI_2.get(), GasLiquidContactorgui2Screen::new);
		event.register(BreakingChemistryModMenus.DRYER_GUI.get(), DryerGUIScreen::new);
		event.register(BreakingChemistryModMenus.BOX_VAPE_GUI.get(), BoxVapeGUIScreen::new);
	}

	public interface ScreenAccessor {
		void updateMenuState(int elementType, String name, Object elementState);
	}
}