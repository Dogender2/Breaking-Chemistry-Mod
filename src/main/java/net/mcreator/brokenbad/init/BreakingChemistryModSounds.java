/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

import net.mcreator.brokenbad.BreakingChemistryMod;

public class BreakingChemistryModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(Registries.SOUND_EVENT, BreakingChemistryMod.MODID);
	public static final DeferredHolder<SoundEvent, SoundEvent> VAPE_SOUND = REGISTRY.register("vape_sound", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath("breaking_chemistry", "vape_sound")));
}