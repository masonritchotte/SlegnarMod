package com.slegnar.slegnarmod.datagen;

import com.slegnar.slegnarmod.Registration;
import com.slegnar.slegnarmod.SlegnarMod;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class SlegnarLanguageProvider extends LanguageProvider{
    
    public SlegnarLanguageProvider(PackOutput output, String locale) {
        super(output, SlegnarMod.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add(Registration.SIMPLE_BLOCK.get(), "Simple Block");
        add(Registration.COMPLEX_BLOCK.get(), "Complex Block");
    }
}
