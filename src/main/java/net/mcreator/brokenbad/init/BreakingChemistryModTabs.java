/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.brokenbad.BreakingChemistryMod;

@EventBusSubscriber
public class BreakingChemistryModTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BreakingChemistryMod.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BROKEN_BAD = REGISTRY.register("broken_bad",
			() -> CreativeModeTab.builder().title(Component.translatable("item_group.breaking_chemistry.broken_bad")).icon(() -> new ItemStack(BreakingChemistryModItems.WEED.get())).displayItems((parameters, tabData) -> {
				tabData.accept(BreakingChemistryModItems.HEMP_SEEDS.get());
				tabData.accept(BreakingChemistryModBlocks.HEMP_POT.get().asItem());
				tabData.accept(BreakingChemistryModItems.WEED.get());
				tabData.accept(BreakingChemistryModItems.ROUND_BOTTOMED_FLASK.get());
				tabData.accept(BreakingChemistryModBlocks.HEATING_MANTLE.get().asItem());
				tabData.accept(BreakingChemistryModItems.CHEMICAL_CONDENSER.get());
				tabData.accept(BreakingChemistryModBlocks.MAGNETICSTIRRER.get().asItem());
				tabData.accept(BreakingChemistryModBlocks.CHEMICALTABLE.get().asItem());
				tabData.accept(BreakingChemistryModBlocks.GROWLIGHT.get().asItem());
				tabData.accept(BreakingChemistryModItems.SALT.get());
				tabData.accept(BreakingChemistryModBlocks.SALTORE.get().asItem());
				tabData.accept(BreakingChemistryModItems.GAS_TANK.get());
				tabData.accept(BreakingChemistryModItems.JOINT.get());
				tabData.accept(BreakingChemistryModItems.METH.get());
				tabData.accept(BreakingChemistryModBlocks.LABORATORYSEPARATOR.get().asItem());
				tabData.accept(BreakingChemistryModItems.POSTREACTIONMIXTURE.get());
				tabData.accept(BreakingChemistryModItems.INGREDIENTSINTHEBOTTLE.get());
				tabData.accept(BreakingChemistryModItems.COLDTABLETS.get());
				tabData.accept(BreakingChemistryModItems.PHOSPHORUSPOWDER.get());
				tabData.accept(BreakingChemistryModBlocks.PHOSPHORUS_ORE.get().asItem());
				tabData.accept(BreakingChemistryModItems.DISINFECTANT_LIQUID.get());
				tabData.accept(BreakingChemistryModItems.SULFUR.get());
				tabData.accept(BreakingChemistryModBlocks.SULFURORE.get().asItem());
				tabData.accept(BreakingChemistryModItems.TRAY.get());
				tabData.accept(BreakingChemistryModItems.FILTERED_ACID_SOLUTION.get());
				tabData.accept(BreakingChemistryModItems.WET_BLUE_CRYSTALS.get());
				tabData.accept(BreakingChemistryModBlocks.GAS_LIQUID_CONTACTOR.get().asItem());
				tabData.accept(BreakingChemistryModItems.HYDROGENCHLORIDE_IN_TANK.get());
				tabData.accept(BreakingChemistryModBlocks.DRYER.get().asItem());
				tabData.accept(BreakingChemistryModItems.BOX_VAPE.get());
				tabData.accept(BreakingChemistryModItems.VAPE_COIL.get());
				tabData.accept(BreakingChemistryModItems.VAPELQ.get());
				tabData.accept(BreakingChemistryModItems.STEEL_SPRING.get());
				tabData.accept(BreakingChemistryModBlocks.DEALERCACHE.get().asItem());
			}).build());

	@SubscribeEvent
	public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
		if (tabData.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(BreakingChemistryModItems.FAT_BUNNY_SPAWN_EGG.get());
		}
	}
}