package com.slegnar.slegnarmod;

import com.slegnar.slegnarmod.blocks.SimpleBlock;
import com.slegnar.slegnarmod.client.entities.AmethystGolem;

import javax.swing.text.html.parser.Entity;

import com.slegnar.slegnarmod.blocks.ComplexBlock;
import com.slegnar.slegnarmod.blocks.ComplexBlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class Registration {
    
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SlegnarMod.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SlegnarMod.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, SlegnarMod.MODID);
    //For Entities
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SlegnarMod.MODID);

    public static final RegistryObject<SimpleBlock> SIMPLE_BLOCK = BLOCKS.register("simple_block", SimpleBlock::new);
    public static final RegistryObject<Item> SIMPLE_BLOCK_ITEM = ITEMS.register("simple_block", () ->
            new BlockItem(SIMPLE_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<ComplexBlock> COMPLEX_BLOCK = BLOCKS.register("complex_block", ComplexBlock::new);
    public static final RegistryObject<Item> COMPLEX_BLOCK_ITEM = ITEMS.register("complex_block", () ->
            new BlockItem(COMPLEX_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<BlockEntityType<ComplexBlockEntity>> COMPLEX_BLOCK_ENTITY = BLOCK_ENTITIES.register("complex_block", () ->
            BlockEntityType.Builder.of(ComplexBlockEntity::new, COMPLEX_BLOCK.get()).build(null));

    //For Entities
    public static final RegistryObject<EntityType<AmethystGolem>> AMETHYST_GOLEM = ENTITY_TYPES.register("amethyst_golem", () ->
            EntityType.Builder.of(AmethystGolem::new, MobCategory.CREATURE).sized(1.25F, 1.5F).build("amethyst_golem"));

    public static void init(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
    }

    static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(SIMPLE_BLOCK_ITEM);
            event.accept(COMPLEX_BLOCK_ITEM);
        }
    }
}
