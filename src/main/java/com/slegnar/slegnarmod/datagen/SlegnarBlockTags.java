package com.slegnar.slegnarmod.datagen;

import java.util.concurrent.CompletableFuture;

import javax.annotation.Nullable;

import com.slegnar.slegnarmod.Registration;
import com.slegnar.slegnarmod.SlegnarMod;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SlegnarBlockTags  extends BlockTagsProvider {
    
    public SlegnarBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SlegnarMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
            Registration.COMPLEX_BLOCK.get(),
            Registration.SIMPLE_BLOCK.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(Registration.COMPLEX_BLOCK.get(), Registration.SIMPLE_BLOCK.get());
    }
}
