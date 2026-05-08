// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

public class Modelfat_bunny<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation("modid", "fat_bunny"), "main");
	private final ModelPart all;
	private final ModelPart head;
	private final ModelPart uszka;
	private final ModelPart tors;
	private final ModelPart nogi_tyl;
	private final ModelPart nogi_przod;

	public Modelfat_bunny(ModelPart root) {
		this.all = root.getChild("all");
		this.head = this.all.getChild("head");
		this.uszka = this.head.getChild("uszka");
		this.tors = this.all.getChild("tors");
		this.nogi_tyl = this.all.getChild("nogi_tyl");
		this.nogi_przod = this.all.getChild("nogi_przod");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition all = partdefinition.addOrReplaceChild("all", CubeListBuilder.create(),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = all.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 18).addBox(-3.0F, -3.0F,
				-3.0F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -4.0F));

		PartDefinition uszka = head.addOrReplaceChild("uszka", CubeListBuilder.create(),
				PartPose.offset(0.0F, -3.0F, -1.0F));

		PartDefinition cube_r1 = uszka.addOrReplaceChild("cube_r1",
				CubeListBuilder.create().texOffs(28, 24).addBox(-1.0F, -5.0F, -0.5F, 2.0F, 5.0F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.6F, 0.5F, -0.5F, 0.0F, 0.0F, -0.5236F));

		PartDefinition cube_r2 = uszka
				.addOrReplaceChild("cube_r2",
						CubeListBuilder.create().texOffs(28, 18).addBox(-1.0F, -5.0F, -0.5F, 2.0F, 5.0F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(1.7F, 0.5F, -0.5F, 0.0F, 0.0F, 0.5236F));

		PartDefinition tors = all.addOrReplaceChild("tors",
				CubeListBuilder.create().texOffs(0, 0)
						.addBox(-5.0F, -9.0F, -5.0F, 10.0F, 8.0F, 10.0F, new CubeDeformation(0.0F)).texOffs(28, 30)
						.addBox(-1.0F, -5.0F, 5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition nogi_tyl = all.addOrReplaceChild("nogi_tyl",
				CubeListBuilder.create().texOffs(20, 18)
						.addBox(-6.0F, -1.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(20, 25)
						.addBox(4.0F, -1.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -4.0F, 6.0F));

		PartDefinition nogi_przod = all.addOrReplaceChild("nogi_przod",
				CubeListBuilder.create().texOffs(0, 26)
						.addBox(-6.0F, -1.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(8, 26)
						.addBox(4.0F, -1.0F, -2.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -4.0F, -4.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		all.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.head.yRot = netHeadYaw / (180F / (float) Math.PI);
		this.head.xRot = headPitch / (180F / (float) Math.PI);
		this.nogi_tyl.xRot = Mth.cos(limbSwing * 1.0F) * -1.0F * limbSwingAmount;
		this.nogi_przod.xRot = Mth.cos(limbSwing * 1.0F) * 1.0F * limbSwingAmount;
	}
}