/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

import net.mcreator.brokenbad.BreakingChemistryMod;

public class BreakingChemistryModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(Registries.PARTICLE_TYPE, BreakingChemistryMod.MODID);
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> HEMP_PARTICLE = REGISTRY.register("hemp_particle", () -> new SimpleParticleType(false));
}