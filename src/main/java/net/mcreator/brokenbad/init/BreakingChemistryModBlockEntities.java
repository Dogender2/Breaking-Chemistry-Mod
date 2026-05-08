/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.registries.BuiltInRegistries;

import net.mcreator.brokenbad.block.entity.*;
import net.mcreator.brokenbad.BreakingChemistryMod;

@EventBusSubscriber
public class BreakingChemistryModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, BreakingChemistryMod.MODID);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HempPotBlockEntity>> HEMP_POT = register("hemp_pot", BreakingChemistryModBlocks.HEMP_POT, HempPotBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HeatingMantleBlockEntity>> HEATING_MANTLE = register("heating_mantle", BreakingChemistryModBlocks.HEATING_MANTLE, HeatingMantleBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MagneticstirrerBlockEntity>> MAGNETICSTIRRER = register("magneticstirrer", BreakingChemistryModBlocks.MAGNETICSTIRRER, MagneticstirrerBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChemicaltableBlockEntity>> CHEMICALTABLE = register("chemicaltable", BreakingChemistryModBlocks.CHEMICALTABLE, ChemicaltableBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LaboratoryseparatorBlockEntity>> LABORATORYSEPARATOR = register("laboratoryseparator", BreakingChemistryModBlocks.LABORATORYSEPARATOR, LaboratoryseparatorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GasLiquidContactorBlockEntity>> GAS_LIQUID_CONTACTOR = register("gas_liquid_contactor", BreakingChemistryModBlocks.GAS_LIQUID_CONTACTOR, GasLiquidContactorBlockEntity::new);
	public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<DryerBlockEntity>> DRYER = register("dryer", BreakingChemistryModBlocks.DRYER, DryerBlockEntity::new);

	// Start of user code block custom block entities
	// End of user code block custom block entities
	private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(String registryname, DeferredHolder<Block, Block> block, BlockEntityType.BlockEntitySupplier<T> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}

	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, HEMP_POT.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, HEMP_POT.get(), (blockEntity, side) -> blockEntity.getFluidTank());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, HEATING_MANTLE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, HEATING_MANTLE.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, MAGNETICSTIRRER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, MAGNETICSTIRRER.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, CHEMICALTABLE.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, CHEMICALTABLE.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, LABORATORYSEPARATOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, LABORATORYSEPARATOR.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, GAS_LIQUID_CONTACTOR.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, GAS_LIQUID_CONTACTOR.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
		event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, DRYER.get(), SidedInvWrapper::new);
		event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, DRYER.get(), (blockEntity, side) -> blockEntity.getEnergyStorage());
	}
}