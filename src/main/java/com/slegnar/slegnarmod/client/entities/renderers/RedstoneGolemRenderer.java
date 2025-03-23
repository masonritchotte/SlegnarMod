package com.slegnar.slegnarmod.client.entities.renderers;

import com.slegnar.slegnarmod.SlegnarMod;
import com.slegnar.slegnarmod.client.entities.RedstoneGolem;
import com.slegnar.slegnarmod.client.entities.models.RedstoneGolemModel;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RedstoneGolemRenderer extends MobRenderer<RedstoneGolem, RedstoneGolemModel<RedstoneGolem>>{

    public static final ModelLayerLocation MODEL = new ModelLayerLocation(new ResourceLocation(SlegnarMod.MODID, "redstone_golem_layer"), "main");

    public RedstoneGolemRenderer(Context pContext) {
        super(pContext, new RedstoneGolemModel<>(pContext.bakeLayer(MODEL)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(RedstoneGolem pEntity) {
        return new ResourceLocation(SlegnarMod.MODID, "textures/redstone_golem.png");
    }
}