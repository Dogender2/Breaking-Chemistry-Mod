/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.core.registries.Registries;

import net.mcreator.brokenbad.procedures.MethHighEffectExpiresProcedure;
import net.mcreator.brokenbad.potion.MethHighMobEffect;
import net.mcreator.brokenbad.BreakingChemistryMod;

@EventBusSubscriber
public class BreakingChemistryModMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(Registries.MOB_EFFECT, BreakingChemistryMod.MODID);
	public static final DeferredHolder<MobEffect, MobEffect> METH_HIGH = REGISTRY.register("meth_high", () -> new MethHighMobEffect());

	@SubscribeEvent
	public static void onEffectRemoved(MobEffectEvent.Remove event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	@SubscribeEvent
	public static void onEffectExpired(MobEffectEvent.Expired event) {
		MobEffectInstance effectInstance = event.getEffectInstance();
		if (effectInstance != null) {
			expireEffects(event.getEntity(), effectInstance);
		}
	}

	private static void expireEffects(Entity entity, MobEffectInstance effectInstance) {
		if (effectInstance.getEffect().is(METH_HIGH)) {
			MethHighEffectExpiresProcedure.execute(entity);
		}
	}
}