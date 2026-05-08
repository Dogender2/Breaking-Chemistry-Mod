/*
*	MCreator note: This file will be REGENERATED on each build.
*/
package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.common.BasicItemListing;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.npc.VillagerProfession;

@EventBusSubscriber
public class BreakingChemistryModTrades {
	@SubscribeEvent
	public static void registerTrades(VillagerTradesEvent event) {
		if (event.getType() == VillagerProfession.CLERIC) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 10), new ItemStack(BreakingChemistryModItems.DISINFECTANT_LIQUID.get()), 3, 5, 0.05f));
		}
		if (event.getType() == VillagerProfession.CLERIC) {
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 7), new ItemStack(BreakingChemistryModItems.COLDTABLETS.get()), 2, 5, 0.05f));
		}
		if (event.getType() == BreakingChemistryModVillagerProfessions.DEALER_VILLAGER.get()) {
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 10), new ItemStack(BreakingChemistryModItems.VAPE_COIL.get()), 3, 3, 0.05f));
			event.getTrades().get(1).add(new BasicItemListing(new ItemStack(Items.EMERALD, 40), new ItemStack(BreakingChemistryModItems.VAPELQ.get()), 4, 3, 0.05f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 30), new ItemStack(BreakingChemistryModItems.JOINT.get()), 3, 5, 0.05f));
			event.getTrades().get(2).add(new BasicItemListing(new ItemStack(Items.EMERALD, 70), new ItemStack(BreakingChemistryModItems.METH.get()), 2, 10, 0.05f));
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(BreakingChemistryModItems.WEED.get()), new ItemStack(Items.EMERALD, 5), 10, 3, 0.05f));
			event.getTrades().get(3).add(new BasicItemListing(new ItemStack(Items.EMERALD, 11), new ItemStack(BreakingChemistryModItems.COLDTABLETS.get()), 4, 4, 0.05f));
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Items.EMERALD, 11), new ItemStack(BreakingChemistryModItems.DISINFECTANT_LIQUID.get()), 4, 5, 0.05f));
			event.getTrades().get(4).add(new BasicItemListing(new ItemStack(Items.EMERALD, 20), new ItemStack(BreakingChemistryModItems.HEMP_SEEDS.get()), 3, 5, 0.05f));
			event.getTrades().get(5).add(new BasicItemListing(new ItemStack(BreakingChemistryModItems.METH.get()), new ItemStack(Items.EMERALD, 50), 5, 5, 0.05f));
		}
	}
}