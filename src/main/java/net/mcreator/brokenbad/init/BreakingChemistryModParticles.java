/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.brokenbad.client.particle.HempParticleParticle;

@EventBusSubscriber(Dist.CLIENT)
public class BreakingChemistryModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(BreakingChemistryModParticleTypes.HEMP_PARTICLE.get(), HempParticleParticle::provider);
	}
}