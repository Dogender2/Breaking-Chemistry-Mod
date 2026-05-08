package net.mcreator.brokenbad.potion;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.brokenbad.procedures.MethHighEffectStartedappliedProcedure;
import net.mcreator.brokenbad.BreakingChemistryMod;

public class MethHighMobEffect extends MobEffect {
	public MethHighMobEffect() {
		super(MobEffectCategory.NEUTRAL, -13050113);
		this.addAttributeModifier(Attributes.SAFE_FALL_DISTANCE, ResourceLocation.fromNamespaceAndPath(BreakingChemistryMod.MODID, "effect.meth_high_0"), 255, AttributeModifier.Operation.ADD_VALUE);
	}

	@Override
	public void onEffectStarted(LivingEntity entity, int amplifier) {
		MethHighEffectStartedappliedProcedure.execute(entity);
	}
}