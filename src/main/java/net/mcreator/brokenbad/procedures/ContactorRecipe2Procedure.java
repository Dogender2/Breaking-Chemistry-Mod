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

public class ContactorRecipe2Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 2000) {
			if (getAmountInGUISlot(entity, 6) < 1) {
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu2 ? _menu2.getSlots().get(3).getItem() : ItemStack.EMPTY).getItem() == BreakingChemistryModItems.SALT
						.get()) {
					if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu4 ? _menu4.getSlots().get(4).getItem() : ItemStack.EMPTY)
							.getItem() == BreakingChemistryModItems.SULFUR.get()) {
						if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu6 ? _menu6.getSlots().get(5).getItem() : ItemStack.EMPTY)
								.getItem() == BreakingChemistryModItems.GAS_TANK.get()) {
							if (entity instanceof Player _player && _player.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu) {
								_menu.getSlots().get(3).remove(1);
								_menu.getSlots().get(4).remove(1);
								_menu.getSlots().get(5).remove(1);
								ItemStack _setstack11 = new ItemStack(BreakingChemistryModItems.HYDROGENCHLORIDE_IN_TANK.get()).copy();
								_setstack11.setCount(1);
								_menu.getSlots().get(6).set(_setstack11);
								_player.containerMenu.broadcastChanges();
							}
							if (world instanceof ILevelExtension _ext) {
								IEnergyStorage _entityStorage = _ext.getCapability(Capabilities.EnergyStorage.BLOCK, BlockPos.containing(x, y, z), null);
								if (_entityStorage != null)
									_entityStorage.extractEnergy(2000, false);
							}
						}
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