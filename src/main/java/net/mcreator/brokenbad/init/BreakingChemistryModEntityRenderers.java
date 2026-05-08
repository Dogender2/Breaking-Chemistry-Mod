/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.brokenbad.init;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.api.distmarker.Dist;

import net.mcreator.brokenbad.client.renderer.FatBunnyRenderer;

@EventBusSubscriber(Dist.CLIENT)
public class BreakingChemistryModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(BreakingChemistryModEntities.FAT_BUNNY.get(), FatBunnyRenderer::new);
	}
}