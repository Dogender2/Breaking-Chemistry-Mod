package net.mcreator.brokenbad.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;

import net.mcreator.brokenbad.init.BreakingChemistryModItems;

public class BoxVapeOnDroppedItemEntityDestroyedProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == BreakingChemistryModItems.BOX_VAPE.get()) {
			if (entity instanceof LivingEntity _entity) {
				ItemStack _setstack2 = new ItemStack(BreakingChemistryModItems.BOX_VAPE_WITHOUT_COIL.get()).copy();
				_setstack2.setCount(1);
				_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack2);
				if (_entity instanceof Player _player)
					_player.getInventory().setChanged();
			}
		}
	}
}