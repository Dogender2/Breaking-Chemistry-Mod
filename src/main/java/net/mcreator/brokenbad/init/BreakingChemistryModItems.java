/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import net.mcreator.brokenbad.item.inventory.BoxVapeWithoutCoilInventoryCapability;
import net.mcreator.brokenbad.item.*;
import net.mcreator.brokenbad.BreakingChemistryMod;

@EventBusSubscriber
public class BreakingChemistryModItems {
	public static final DeferredRegister.Items REGISTRY = DeferredRegister.createItems(BreakingChemistryMod.MODID);
	public static final DeferredItem<Item> HEMP_SEEDS;
	public static final DeferredItem<Item> HEMP_POT;
	public static final DeferredItem<Item> WEED;
	public static final DeferredItem<Item> ROUND_BOTTOMED_FLASK;
	public static final DeferredItem<Item> HEATING_MANTLE;
	public static final DeferredItem<Item> CHEMICAL_CONDENSER;
	public static final DeferredItem<Item> MAGNETICSTIRRER;
	public static final DeferredItem<Item> CHEMICALTABLE;
	public static final DeferredItem<Item> GROWLIGHT;
	public static final DeferredItem<Item> SALT;
	public static final DeferredItem<Item> SALTORE;
	public static final DeferredItem<Item> GAS_TANK;
	public static final DeferredItem<Item> JOINT;
	public static final DeferredItem<Item> METH;
	public static final DeferredItem<Item> FAT_BUNNY_SPAWN_EGG;
	public static final DeferredItem<Item> LABORATORYSEPARATOR;
	public static final DeferredItem<Item> POSTREACTIONMIXTURE;
	public static final DeferredItem<Item> INGREDIENTSINTHEBOTTLE;
	public static final DeferredItem<Item> COLDTABLETS;
	public static final DeferredItem<Item> PHOSPHORUSPOWDER;
	public static final DeferredItem<Item> PHOSPHORUS_ORE;
	public static final DeferredItem<Item> DISINFECTANT_LIQUID;
	public static final DeferredItem<Item> SULFUR;
	public static final DeferredItem<Item> SULFURORE;
	public static final DeferredItem<Item> TRAY;
	public static final DeferredItem<Item> FILTERED_ACID_SOLUTION;
	public static final DeferredItem<Item> WET_BLUE_CRYSTALS;
	public static final DeferredItem<Item> GAS_LIQUID_CONTACTOR;
	public static final DeferredItem<Item> HYDROGENCHLORIDE_IN_TANK;
	public static final DeferredItem<Item> DRYER;
	public static final DeferredItem<Item> BOX_VAPE;
	public static final DeferredItem<Item> BOX_VAPE_WITHOUT_COIL;
	public static final DeferredItem<Item> VAPE_COIL;
	public static final DeferredItem<Item> VAPELQ;
	public static final DeferredItem<Item> STEEL_SPRING;
	public static final DeferredItem<Item> DEALERCACHE;
	static {
		HEMP_SEEDS = REGISTRY.register("hemp_seeds", HempSeedsItem::new);
		HEMP_POT = block(BreakingChemistryModBlocks.HEMP_POT);
		WEED = REGISTRY.register("weed", WeedItem::new);
		ROUND_BOTTOMED_FLASK = REGISTRY.register("round_bottomed_flask", RoundBottomedFlaskItem::new);
		HEATING_MANTLE = block(BreakingChemistryModBlocks.HEATING_MANTLE);
		CHEMICAL_CONDENSER = REGISTRY.register("chemical_condenser", ChemicalcondenserItem::new);
		MAGNETICSTIRRER = block(BreakingChemistryModBlocks.MAGNETICSTIRRER, new Item.Properties().stacksTo(1));
		CHEMICALTABLE = block(BreakingChemistryModBlocks.CHEMICALTABLE);
		GROWLIGHT = block(BreakingChemistryModBlocks.GROWLIGHT);
		SALT = REGISTRY.register("salt", SaltItem::new);
		SALTORE = block(BreakingChemistryModBlocks.SALTORE);
		GAS_TANK = REGISTRY.register("gas_tank", GasTankItem::new);
		JOINT = REGISTRY.register("joint", JointItem::new);
		METH = REGISTRY.register("meth", MethItem::new);
		FAT_BUNNY_SPAWN_EGG = REGISTRY.register("fat_bunny_spawn_egg", () -> new DeferredSpawnEggItem(BreakingChemistryModEntities.FAT_BUNNY, -2697514, -33668, new Item.Properties()));
		LABORATORYSEPARATOR = block(BreakingChemistryModBlocks.LABORATORYSEPARATOR);
		POSTREACTIONMIXTURE = REGISTRY.register("postreactionmixture", PostreactionmixtureItem::new);
		INGREDIENTSINTHEBOTTLE = REGISTRY.register("ingredientsinthebottle", IngredientsinthebottleItem::new);
		COLDTABLETS = REGISTRY.register("coldtablets", ColdtabletsItem::new);
		PHOSPHORUSPOWDER = REGISTRY.register("phosphoruspowder", PhosphoruspowderItem::new);
		PHOSPHORUS_ORE = block(BreakingChemistryModBlocks.PHOSPHORUS_ORE);
		DISINFECTANT_LIQUID = REGISTRY.register("disinfectant_liquid", DisinfectantLiquidItem::new);
		SULFUR = REGISTRY.register("sulfur", SulfurItem::new);
		SULFURORE = block(BreakingChemistryModBlocks.SULFURORE);
		TRAY = REGISTRY.register("tray", TrayItem::new);
		FILTERED_ACID_SOLUTION = REGISTRY.register("filtered_acid_solution", FilteredAcidSolutionItem::new);
		WET_BLUE_CRYSTALS = REGISTRY.register("wet_blue_crystals", WetBlueCrystalsItem::new);
		GAS_LIQUID_CONTACTOR = block(BreakingChemistryModBlocks.GAS_LIQUID_CONTACTOR);
		HYDROGENCHLORIDE_IN_TANK = REGISTRY.register("hydrogenchloride_in_tank", HydrogenchlorideInTankItem::new);
		DRYER = block(BreakingChemistryModBlocks.DRYER);
		BOX_VAPE = REGISTRY.register("box_vape", BoxVapeItem::new);
		BOX_VAPE_WITHOUT_COIL = REGISTRY.register("box_vape_without_coil", BoxVapeWithoutCoilItem::new);
		VAPE_COIL = REGISTRY.register("vape_coil", VapeCoilItem::new);
		VAPELQ = REGISTRY.register("vapelq", VapelqItem::new);
		STEEL_SPRING = REGISTRY.register("steel_spring", SteelSpringItem::new);
		DEALERCACHE = block(BreakingChemistryModBlocks.DEALERCACHE);
	}

	// Start of user code block custom items
	// End of user code block custom items
	@SubscribeEvent
	public static void registerCapabilities(RegisterCapabilitiesEvent event) {
		event.registerItem(Capabilities.ItemHandler.ITEM, (stack, context) -> new BoxVapeWithoutCoilInventoryCapability(stack), BOX_VAPE_WITHOUT_COIL.get());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block) {
		return block(block, new Item.Properties());
	}

	private static DeferredItem<Item> block(DeferredHolder<Block, Block> block, Item.Properties properties) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), properties));
	}
}