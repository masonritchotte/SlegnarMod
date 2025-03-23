package com.slegnar.slegnarmod.client;

import com.slegnar.slegnarmod.Registration;
import com.slegnar.slegnarmod.SlegnarMod;
import com.slegnar.slegnarmod.client.entities.models.RedstoneGolemModel;
import com.slegnar.slegnarmod.client.entities.models.AmethystGolemModel;
import com.slegnar.slegnarmod.client.entities.renderers.AmethystGolemRenderer;
import com.slegnar.slegnarmod.client.entities.renderers.RedstoneGolemRenderer;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = SlegnarMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    
    @SubscribeEvent
    public static void initClient(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(Registration.COMPLEX_BLOCK_ENTITY.get(), ComplexBlockRenderer::new);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(Registration.AMETHYST_GOLEM.get(), AmethystGolemRenderer::new);
        EntityRenderers.register(Registration.REDSTONE_GOLEM.get(), RedstoneGolemRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(SlegnarMod.MODID, "amethyst_golem_layer"), "main"), AmethystGolemModel::createBodyLayer);
        event.registerLayerDefinition(new ModelLayerLocation(new ResourceLocation(SlegnarMod.MODID, "redstone_golem_layer"), "main"), RedstoneGolemModel::createBodyLayer);
    }
}
