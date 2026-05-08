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

public class HeatingMantleCraftingProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (getEnergyStored(world, BlockPos.containing(x, y, z), null) >= 2000) {
			if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu1 ? _menu1.getSlots().get(0).getItem() : ItemStack.EMPTY)
					.getItem() == BreakingChemistryModItems.ROUND_BOTTOMED_FLASK.get()) {
				if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu3 ? _menu3.getSlots().get(1).getItem() : ItemStack.EMPTY)
						.getItem() == BreakingChemistryModItems.CHEMICAL_CONDENSER.get()) {
					if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu5 ? _menu5.getSlots().get(2).getItem() : ItemStack.EMPTY)
							.getItem() == BreakingChemistryModItems.COLDTABLETS.get()) {
						if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu7 ? _menu7.getSlots().get(3).getItem() : ItemStack.EMPTY)
								.getItem() == BreakingChemistryModItems.PHOSPHORUSPOWDER.get()) {
							if ((entity instanceof Player _plrSlotItem && _plrSlotItem.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu9 ? _menu9.getSlots().get(4).getItem() : ItemStack.EMPTY)
									.getItem() == BreakingChemistryModItems.DISINFECTANT_LIQUID.get()) {
								if (getAmountInGUISlot(entity, 5) < 16) {
									if (entity instanceof Player _player && _player.containerMenu instanceof BreakingChemistryModMenus.MenuAccessor _menu) {
										_menu.getSlots().get(0).remove(1);
										_menu.getSlots().get(2).remove(1);
										_menu.getSlots().get(3).remove(1);
										_menu.getSlots().get(4).remove(1);
										ItemStack _setstack17 = new ItemStack(BreakingChemistryModItems.INGREDIENTSINTHEBOTTLE.get()).copy();
										_setstack17.setCount(getAmountInGUISlot(entity, 5) + 1);
										_menu.getSlots().get(5).set(_setstack17);
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