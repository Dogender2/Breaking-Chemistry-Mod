package net.mcreator.brokenbad.procedures;

import net.neoforged.neoforge.energy.IEnergyStorage;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.brokenbad.init.BreakingChemistryModMenus;
import net.mcreator.brokenbad.init.BreakingChemistryModItems;

public class MagneticStirrerCraftingProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 1000) {
			if (getAmountInGUISlot(entity, 1) < 16) {
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(0).getItem() : ItemStack.EMPTY)
						.getItem() == BreakingChemistryModItems.INGREDIENTSINTHEBOTTLE.get()) {
					if (entity instanceof Player _player && _player.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu) {
						_menu.getSlots().get(0).remove(1);
						ItemStack _setstack6 = new ItemStack(BreakingChemistryModItems.POSTREACTIONMIXTURE.get()).copy();
						_setstack6.setCount(getAmountInGUISlot(entity, 1) + 1);
						_menu.getSlots().get(1).set(_setstack6);
						_player.containerMenu.broadcastChanges();
					}
					if (world instanceof ILevelExtension _ext) {
						IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, BlockPos.containing(x, y, z), null);
						if (_entityStorage != null)
							_entityStorage.extractEnergy(1000, false);
					}
				}
			}
		}
	}

	public static int getEnergyStored(LevelAccessor level, BlockPos pos, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			IEnergyStorage energyStorage = levelExtension.getCapability(Capabilities.EnergyStorage.BLOCK, pos, direction);
			if (energyStorage != null)
				return energyStorage.getEnergyStored();
		}
		return 0;
	}

	private static int getAmountInGUISlot(Entity entity, int sltid) {
		if (entity instanceof Player player && player.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor menuAccessor) {
			ItemStack stack = menuAccessor.getSlots().get(sltid).getItem();
			if (stack != null)
				return stack.getCount();
		}
		return 0;
	}
}