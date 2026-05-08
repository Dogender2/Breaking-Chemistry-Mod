package net.mcreator.brokenbad.item.inventory;

import net.neoforged.neoforge.items.ComponentItemHandler;
import net.neoforged.neoforge.event.entity.item.ItemTossEvent;
import net.neoforged.neoforge.common.MutableDataComponentHolder;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.component.DataComponents;

import net.mcreator.brokenbad.world.inventory.BoxVapeGUIMenu;
import net.mcreator.brokenbad.init.BreakingChemistryModItems;

import javax.annotation.Nonnull;

@EventBusSubscriber
public class BoxVapeWithoutCoilInventoryCapability extends ComponentItemHandler {
	@SubscribeEvent
	public static void onItemDropped(ItemTossEvent event) {
		if (event.getEntity().getItem().getItem() == BreakingChemistryModItems.BOX_VAPE_WITHOUT_COIL.get()) {
			Player player = event.getPlayer();
			if (player.containerMenu instanceof BoxVapeGUIMenu)
				player.closeContainer();
		}
	}

	public BoxVapeWithoutCoilInventoryCapability(MutableDataComponentHolder parent) {
		super(parent, DataComponents.CONTAINER, 2);
	}

	@Override
	public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
		return stack.getItem() != BreakingChemistryModItems.BOX_VAPE_WITHOUT_COIL.get();
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return super.getStackInSlot(slot).copy();
	}
}