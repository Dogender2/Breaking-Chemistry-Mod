package net.mcreator.brokenbad.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.server.level.ServerLevel;

import net.mcreator.brokenbad.network.BreakingChemistryModVariables;
import net.mcreator.brokenbad.BreakingChemistryMod;

public class JointPlayerFinishesUsingItemProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level) {
			itemstack.hurtAndBreak(1, _level, null, _stkprov -> {
			});
		}
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 2, false, false));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 100, 2, false, false));
		if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 1, false, false));
		{
			BreakingChemistryModVariables.PlayerVariables _vars = entity.getData(BreakingChemistryModVariables.PLAYER_VARIABLES);
			_vars.showGreenOverlay = true;
			_vars.markSyncDirty();
		}
		ShowOverlay10sProcedure.execute(entity);
		BreakingChemistryMod.queueServerWork(200, () -> {
			{
				BreakingChemistryModVariables.PlayerVariables _vars = entity.getData(BreakingChemistryModVariables.PLAYER_VARIABLES);
				_vars.showGreenOverlay = false;
				_vars.markSyncDirty();
			}
			ShowOverlay10sProcedure.execute(entity);
		});
	}
}