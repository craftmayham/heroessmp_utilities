package com.craftmayham.hsmputil.entity.client;

import com.craftmayham.hsmputil.entity.animations.ModAnimationDefinitions;
import com.craftmayham.hsmputil.entity.custom.CockroachEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class CockroachModel<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart cockroach;
	private final ModelPart torso;
	private final ModelPart front_right_leg;
	private final ModelPart front_left_leg;
	private final ModelPart middle_right_leg;
	private final ModelPart middle_left_leg;
	private final ModelPart back_right_leg;
	private final ModelPart back_left_leg;
	private final ModelPart right_antenna;
	private final ModelPart left_antenna;
	private final ModelPart right_wing;
	private final ModelPart left_wing;

	public CockroachModel(ModelPart root) {
		this.cockroach = root.getChild("cockroach");
		this.torso = this.cockroach.getChild("torso");
		this.front_right_leg = this.torso.getChild("front_right_leg");
		this.front_left_leg = this.torso.getChild("front_left_leg");
		this.middle_right_leg = this.torso.getChild("middle_right_leg");
		this.middle_left_leg = this.torso.getChild("middle_left_leg");
		this.back_right_leg = this.torso.getChild("back_right_leg");
		this.back_left_leg = this.torso.getChild("back_left_leg");
		this.right_antenna = this.cockroach.getChild("right_antenna");
		this.left_antenna = this.cockroach.getChild("left_antenna");
		this.right_wing = this.left_antenna.getChild("right_wing");
		this.left_wing = this.cockroach.getChild("left_wing");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition cockroach = partdefinition.addOrReplaceChild("cockroach", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition torso = cockroach.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -7.0F, 8.0F, 5.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition front_right_leg = torso.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(-3, 18).addBox(-7.0F, 0.0F, -2.5F, 7.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -3.0F, -3.5F));

		PartDefinition front_left_leg = torso.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(-3, 18).mirror().addBox(0.0F, 0.0F, -2.5F, 7.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, -3.0F, -3.5F));

		PartDefinition middle_right_leg = torso.addOrReplaceChild("middle_right_leg", CubeListBuilder.create().texOffs(-3, 18).addBox(-6.75F, 0.0F, -2.5F, 7.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -2.5F, -0.5F));

		PartDefinition middle_left_leg = torso.addOrReplaceChild("middle_left_leg", CubeListBuilder.create().texOffs(-3, 18).mirror().addBox(-0.25F, 0.0F, -2.5F, 7.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, -2.5F, -0.5F));

		PartDefinition back_right_leg = torso.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(-3, 21).addBox(-7.0F, 0.0F, -2.5F, 9.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.5F, -2.25F, 3.5F));

		PartDefinition back_left_leg = torso.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(-3, 21).mirror().addBox(-0.5F, 0.0F, -2.5F, 9.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, -2.25F, 3.5F));

		PartDefinition right_antenna = cockroach.addOrReplaceChild("right_antenna", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = right_antenna.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(1, -6).addBox(0.0F, -2.0F, -5.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.75F, -4.75F, -7.5F, 0.0F, 0.3927F, 0.0F));

		PartDefinition left_antenna = cockroach.addOrReplaceChild("left_antenna", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = left_antenna.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(1, -6).mirror().addBox(0.0F, -2.0F, -5.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(1.75F, -4.75F, -7.5F, 0.0F, -0.3927F, 0.0F));

		PartDefinition right_wing = left_antenna.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(42, 0).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offset(2.5F, -6.0F, 2.0F));

		PartDefinition left_wing = cockroach.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(42, 10).addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 8.0F, new CubeDeformation(0.2F)), PartPose.offset(-2.5F, -6.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(ModAnimationDefinitions.COCKROACH_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((CockroachEntity) entity).idleAnimationState, ModAnimationDefinitions.COCKROACH_IDLE, ageInTicks, 1F);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		cockroach.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);

	}

	@Override
	public ModelPart root() {
		return cockroach;
	}
}