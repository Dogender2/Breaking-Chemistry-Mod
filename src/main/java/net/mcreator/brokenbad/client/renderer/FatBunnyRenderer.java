package net.mcreator.brokenbad.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.mcreator.brokenbad.entity.FatBunnyEntity;
import net.mcreator.brokenbad.client.model.Modelfat_bunny;

import com.mojang.blaze3d.vertex.PoseStack;

public class FatBunnyRenderer extends MobRenderer<FatBunnyEntity, Modelfat_bunny<FatBunnyEntity>> {
	private final ResourceLocation entityTexture = ResourceLocation.parse("breaking_chemistry:textures/entities/fat_bunny_txt.png");

	public FatBunnyRenderer(EntityRendererProvider.Context context) {
		super(context, new Modelfat_bunny<FatBunnyEntity>(context.bakeLayer(Modelfat_bunny.LAYER_LOCATION)), 0.5f);
	}

	@Override
	protected void scale(FatBunnyEntity entity, PoseStack poseStack, float f) {
		poseStack.scale(entity.getAgeScale(), entity.getAgeScale(), entity.getAgeScale());
	}

	@Override
	public ResourceLocation getTextureLocation(FatBunnyEntity entity) {
		return entityTexture;
	}
}