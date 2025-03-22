package com.slegnar.slegnarmod.client;

import com.slegnar.slegnarmod.Registration;
import com.slegnar.slegnarmod.SlegnarMod;
import com.slegnar.slegnarmod.client.entities.AmethystGolem;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SlegnarMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntitySetup {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(Registration.AMETHYST_GOLEM.get(), AmethystGolem.createAttributes().build());
    }
}
