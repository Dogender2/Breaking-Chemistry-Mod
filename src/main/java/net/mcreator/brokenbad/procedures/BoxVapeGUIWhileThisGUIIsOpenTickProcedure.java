package net.mcreator.brokenbad.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;

import net.mcreator.brokenbad.init.BreakingChemistryModMenus;
import net.mcreator.brokenbad.init.BreakingChemistryModItems;

public class BoxVapeGUIWhileThisGUIIsOpenTickProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu0 ? _menu0.getSlots().get(1).getItem() : ItemStack.EMPTY).getItem() == BreakingChemistryModItems.VAPE_COIL
				.get()) {
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(0).getItem() : ItemStack.EMPTY).getItem() == BreakingChemistryModItems.VAPELQ
					.get()) {
				if (entity instanceof LivingEntity _entity) {
					ItemStack _setstack4 = new ItemStack(BreakingChemistryModItems.BOX_VAPE.get()).copy();
					_setstack4.setCount(1);
					_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack4);
					if (_entity instanceof Player _player)
						_player.getInventory().setChanged();
				}
				if (entity instanceof Player _player)
					_player.closeContainer();
			}
		}
	}
}