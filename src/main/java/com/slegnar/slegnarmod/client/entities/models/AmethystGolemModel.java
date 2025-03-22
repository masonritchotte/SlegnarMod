// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package com.slegnar.slegnarmod.client.entities.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.slegnar.slegnarmod.SlegnarMod;
import com.slegnar.slegnarmod.client.entities.AmethystGolem;
import com.slegnar.slegnarmod.client.entities.animations.AmethystGolemAnimation;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class AmethystGolemModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	// public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(SlegnarMod.MODID, "amethystgolemmodel"), "main");
	private final ModelPart body;
	private final ModelPart leftArm;
	private final ModelPart rightArm;
	private final ModelPart leftLeg;
	private final ModelPart rightLeg;
	private final ModelPart head;

	public AmethystGolemModel(ModelPart root) {
		this.body = root.getChild("body");
		this.leftArm = this.body.getChild("leftArm");
		this.rightArm = this.body.getChild("rightArm");
		this.leftLeg = this.body.getChild("leftLeg");
		this.rightLeg = this.body.getChild("rightLeg");
		this.head = this.body.getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 17).addBox(-5.0F, -12.0F, -4.0F, 10.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-6.0F, -19.0F, -5.0F, 12.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition leftArm = body.addOrReplaceChild("leftArm", CubeListBuilder.create().texOffs(20, 28).addBox(0.0F, -3.0F, -3.0F, 4.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -17.0F, 0.0F));

		PartDefinition rightArm = body.addOrReplaceChild("rightArm", CubeListBuilder.create().texOffs(0, 28).addBox(-4.0F, -3.0F, -3.0F, 4.0F, 14.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -17.0F, 0.0F));

		PartDefinition leftLeg = body.addOrReplaceChild("leftLeg", CubeListBuilder.create().texOffs(40, 33).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -9.0F, 0.0F));

		PartDefinition rightLeg = body.addOrReplaceChild("rightLeg", CubeListBuilder.create().texOffs(44, 0).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 9.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -9.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(34, 22).addBox(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		applyHeadRotation(netHeadYaw, headPitch);
		this.animateWalk(AmethystGolemAnimation.walk, limbSwing, limbSwingAmount, 4f, 2.5f);
		this.animate(((AmethystGolem)entity).attackState, AmethystGolemAnimation.attack, ageInTicks);
	}

	private void applyHeadRotation(float netHeadYaw, float headPitch) {
		netHeadYaw = Mth.clamp(netHeadYaw, -30, 30);
		headPitch = Mth.clamp(headPitch, -25, 45);
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180f);
		this.head.xRot = headPitch * ((float)Math.PI / 180f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return body;
	}
}