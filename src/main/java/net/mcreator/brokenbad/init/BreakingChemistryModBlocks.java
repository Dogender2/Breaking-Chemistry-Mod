/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredBlock;

import net.minecraft.world.level.block.Block;

import net.mcreator.brokenbad.block.*;
import net.mcreator.brokenbad.BreakingChemistryMod;

public class BreakingChemistryModBlocks {
	public static final DeferredRegister.Blocks REGISTRY = DeferredRegister.createBlocks(BreakingChemistryMod.MODID);
	public static final DeferredBlock<Block> HEMP_POT;
	public static final DeferredBlock<Block> HEATING_MANTLE;
	public static final DeferredBlock<Block> MAGNETICSTIRRER;
	public static final DeferredBlock<Block> CHEMICALTABLE;
	public static final DeferredBlock<Block> GROWLIGHT;
	public static final DeferredBlock<Block> SALTORE;
	public static final DeferredBlock<Block> LABORATORYSEPARATOR;
	public static final DeferredBlock<Block> PHOSPHORUS_ORE;
	public static final DeferredBlock<Block> SULFURORE;
	public static final DeferredBlock<Block> GAS_LIQUID_CONTACTOR;
	public static final DeferredBlock<Block> DRYER;
	public static final DeferredBlock<Block> DEALERCACHE;
	static {
		HEMP_POT = REGISTRY.register("hemp_pot", HempPotBlock::new);
		HEATING_MANTLE = REGISTRY.register("heating_mantle", HeatingMantleBlock::new);
		MAGNETICSTIRRER = REGISTRY.register("magneticstirrer", MagneticstirrerBlock::new);
		CHEMICALTABLE = REGISTRY.register("chemicaltable", ChemicaltableBlock::new);
		GROWLIGHT = REGISTRY.register("growlight", GrowlightBlock::new);
		SALTORE = REGISTRY.register("saltore", SaltoreBlock::new);
		LABORATORYSEPARATOR = REGISTRY.register("laboratoryseparator", LaboratoryseparatorBlock::new);
		PHOSPHORUS_ORE = REGISTRY.register("phosphorus_ore", PhosphorusOreBlock::new);
		SULFURORE = REGISTRY.register("sulfurore", SulfuroreBlock::new);
		GAS_LIQUID_CONTACTOR = REGISTRY.register("gas_liquid_contactor", GasLiquidContactorBlock::new);
		DRYER = REGISTRY.register("dryer", DryerBlock::new);
		DEALERCACHE = REGISTRY.register("dealercache", DealercacheBlock::new);
	}
	// Start of user code block custom blocks
	// End of user code block custom blocks
}