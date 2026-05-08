package net.mcreator.brokenbad.mixin;

import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.core.Holder;

import net.mcreator.brokenbad.init.BreakingChemistryModBiomes;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;

@Mixin(NoiseGeneratorSettings.class)
public class NoiseGeneratorSettingsMixin implements BreakingChemistryModBiomes.BreakingChemistryModNoiseGeneratorSettings {
	@Unique
	private Holder<DimensionType> breaking_chemistry_dimensionTypeReference;

	@WrapMethod(method = "surfaceRule")
	public SurfaceRules.RuleSource surfaceRule(Operation<SurfaceRules.RuleSource> original) {
		SurfaceRules.RuleSource retval = original.call();
		if (this.breaking_chemistry_dimensionTypeReference != null) {
			retval = BreakingChemistryModBiomes.adaptSurfaceRule(retval, this.breaking_chemistry_dimensionTypeReference);
		}
		return retval;
	}

	@Override
	public void setbreaking_chemistryDimensionTypeReference(Holder<DimensionType> dimensionType) {
		this.breaking_chemistry_dimensionTypeReference = dimensionType;
	}
}