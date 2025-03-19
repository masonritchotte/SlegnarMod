package com.slegnar.slegnarmod.datagen;

import com.slegnar.slegnarmod.Registration;
import com.slegnar.slegnarmod.SlegnarMod;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SlegnarBlockStates extends BlockStateProvider{

    public SlegnarBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SlegnarMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.SIMPLE_BLOCK.get());
        simpleBlock(Registration.COMPLEX_BLOCK.get());
    }
}
