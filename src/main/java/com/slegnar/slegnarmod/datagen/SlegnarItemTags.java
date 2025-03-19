package com.slegnar.slegnarmod.datagen;

import java.util.concurrent.CompletableFuture;

import com.slegnar.slegnarmod.SlegnarMod;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SlegnarItemTags extends ItemTagsProvider {
    
    public SlegnarItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(packOutput, lookupProvider, blockTags.contentsGetter(), SlegnarMod.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
    }

}
