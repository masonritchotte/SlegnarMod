package com.slegnar.slegnarmod.client;

import com.slegnar.slegnarmod.SlegnarMod;
import com.slegnar.slegnarmod.client.AmethystGolemModel;
import com.slegnar.slegnarmod.client.AmethystGolem;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AmethystGolemRenderer extends MobRenderer<AmethystGolem, AmethystGolemModel<AmethystGolem>> {

    public static final ModelLayerLocation MODEL = new ModelLayerLocation(new ResourceLocation(SlegnarMod.MODID, "amethyst_golem_layer"), "main");

    public AmethystGolemRenderer(Context pContext) {
        super(pContext, new AmethystGolemModel<>(pContext.bakeLayer(MODEL)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(AmethystGolem pEntity) {
        return new ResourceLocation(SlegnarMod.MODID, "textures/amethyst_golem.png");
    }
}