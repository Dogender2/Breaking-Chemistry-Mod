package net.mcreator.brokenbad.procedures;

import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.common.extensions.ILevelExtension;
import net.neoforged.neoforge.capabilities.Capabilities;

import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;

import net.mcreator.brokenbad.network.BreakingChemistryModVariables;
import net.mcreator.brokenbad.init.BreakingChemistryModParticleTypes;
import net.mcreator.brokenbad.init.BreakingChemistryModBlocks;

public class HempPotOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "pot_stage") instanceof IntegerProperty _getip1 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip1) : -1) == 2) {
			BreakingChemistryModVariables.MapVariables.get(world).hempPotCounter = BreakingChemistryModVariables.MapVariables.get(world).hempPotCounter + 1;
			BreakingChemistryModVariables.MapVariables.get(world).markSyncDirty();
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "growth") instanceof IntegerProperty _getip3 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip3) : -1) < 3) {
				if ((world.getBlockState(BlockPos.containing(x, y + 2, z))).getBlock() == BreakingChemistryModBlocks.GROWLIGHT.get()) {
					if (BreakingChemistryModVariables.MapVariables.get(world).hempPotCounter >= 50) {
						if (getFluidTankLevel(world, BlockPos.containing(x, y, z), 1, null) > 1000) {
							{
								int _value = (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "growth") instanceof IntegerProperty _getip8 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip8) : -1) + 1;
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("growth") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
									world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
							}
							if (world instanceof ILevelExtension _ext) {
								IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), null);
								if (_fluidHandler != null)
									_fluidHandler.drain(1000, IFluidHandler.FluidAction.EXECUTE);
							}
							BreakingChemistryModVariables.MapVariables.get(world).hempPotCounter = 0;
							BreakingChemistryModVariables.MapVariables.get(world).markSyncDirty();
						}
					}
				} else {
					if (BreakingChemistryModVariables.MapVariables.get(world).hempPotCounter >= 100) {
						if (getFluidTankLevel(world, BlockPos.containing(x, y, z), 1, null) > 1000) {
							{
								int _value = (getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "growth") instanceof IntegerProperty _getip13 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip13) : -1) + 1;
								BlockPos _pos = BlockPos.containing(x, y, z);
								BlockState _bs = world.getBlockState(_pos);
								if (_bs.getBlock().getStateDefinition().getProperty("growth") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
									world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
							}
							if (world instanceof ILevelExtension _ext) {
								IFluidHandler _fluidHandler = _ext.getCapability(Capabilities.FluidHandler.BLOCK, BlockPos.containing(x, y, z), null);
								if (_fluidHandler != null)
									_fluidHandler.drain(1000, IFluidHandler.FluidAction.EXECUTE);
							}
							BreakingChemistryModVariables.MapVariables.get(world).hempPotCounter = 0;
							BreakingChemistryModVariables.MapVariables.get(world).markSyncDirty();
						}
					}
				}
			}
		}
		if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "pot_stage") instanceof IntegerProperty _getip17 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip17) : -1) == 2) {
			if ((getPropertyByName((world.getBlockState(BlockPos.containing(x, y, z))), "growth") instanceof IntegerProperty _getip19 ? (world.getBlockState(BlockPos.containing(x, y, z))).getValue(_getip19) : -1) >= 2) {
				if (Math.random() < 0.3) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (BreakingChemistryModParticleTypes.HEMP_PARTICLE.get()), (x + 0.5), (y + 1.5), (z + 0.5), Mth.nextInt(RandomSource.create(), 6, 12), 0.3, 0, 0.3, 0.3);
				}
			}
		}
	}

	private static Property<?> getPropertyByName(BlockState state, String name) {
		for (Property<?> property : state.getProperties()) {
			if (property.getName().equals(name)) {
				return property;
			}
		}
		return null;
	}

	private static int getFluidTankLevel(LevelAccessor level, BlockPos pos, int tank, Direction direction) {
		if (level instanceof ILevelExtension levelExtension) {
			IFluidHandler fluidHandler = levelExtension.getCapability(Capabilities.FluidHandler.BLOCK, pos, direction);
			if (fluidHandler != null)
				return fluidHandler.getFluidInTank(tank).getAmount();
		}
		return 0;
	}
}