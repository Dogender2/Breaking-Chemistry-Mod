package net.mcreator.brokenbad.procedures;

import net.minecraft.world.entity.Entity;

import net.mcreator.brokenbad.network.BreakingChemistryModVariables;

public class ShowMethOverlayProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		return entity.getData(BreakingChemistryModVariables.PLAYER_VARIABLES).showMethOverlay;
	}
}