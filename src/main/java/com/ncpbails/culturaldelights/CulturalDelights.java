package com.ncpbails.culturaldelights;

import com.mojang.logging.LogUtils;
import com.ncpbails.culturaldelights.block.ModBlocks;
import com.ncpbails.culturaldelights.block.entity.ModBlockEntities;
import com.ncpbails.culturaldelights.item.ModItems;
import com.ncpbails.culturaldelights.world.biomemods.ModBiomeModifiers;
import com.ncpbails.culturaldelights.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CulturalDelights.MOD_ID)
public class CulturalDelights
{
    public static final String MOD_ID = "culturaldelights";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public CulturalDelights()
    {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        //ModBlockEntities.register(eventBus);
        ModBiomeModifiers.register(eventBus);
        ModPlacedFeatures.register(eventBus);


        eventBus.addListener(this::setup);
        //eventBus.addListener(this::clientSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    //private void clientSetup(final FMLClientSetupEvent event) {
    //    ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_CUCUMBERS.get(), RenderType.cutoutMipped());
    //    ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_CORN.get(), RenderType.cutoutMipped());
    //   ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_EGGPLANTS.get(), RenderType.cutoutMipped());
    //    ItemBlockRenderTypes.setRenderLayer(ModBlocks.AVOCADO_LEAVES.get(), RenderType.cutoutMipped());
    //    ItemBlockRenderTypes.setRenderLayer(ModBlocks.AVOCADO_SAPLING.get(), RenderType.cutoutMipped());
        //ItemBlockRenderTypes.setRenderLayer(ModBlocks.AVOCADO_PIT.get(), RenderType.cutoutMipped());
    //    ItemBlockRenderTypes.setRenderLayer(ModBlocks.CUCUMBERS.get(), RenderType.cutoutMipped());
    //    ItemBlockRenderTypes.setRenderLayer(ModBlocks.EGGPLANTS.get(), RenderType.cutoutMipped());
    //    ItemBlockRenderTypes.setRenderLayer(ModBlocks.CORN.get(), RenderType.cutoutMipped());
    //    ItemBlockRenderTypes.setRenderLayer(ModBlocks.CORN_UPPER.get(), RenderType.cutoutMipped());


        //ModItemProperties.addCustomItemProperties();
    //}

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            registerCompostables();
            //registerAnimalFeeds();
        });

        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");

    }

    public static void registerCompostables() {
        // 30% chance
        ComposterBlock.COMPOSTABLES.put(ModItems.CUCUMBER_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.CORN_KERNELS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.EGGPLANT_SEEDS.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.AVOCADO_PIT.get(), 0.3F);

        // 50% chance
        ComposterBlock.COMPOSTABLES.put(ModItems.CUT_CUCUMBER.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.CUT_AVOCADO.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.CUT_EGGPLANT.get(), 0.65F);

        // 65% chance
        ComposterBlock.COMPOSTABLES.put(ModItems.AVOCADO.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.CUCUMBER.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.CORN_COB.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.EGGPLANT.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModItems.WHITE_EGGPLANT.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.WILD_CUCUMBERS.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.WILD_CORN.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.WILD_EGGPLANTS.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.AVOCADO_LEAVES.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(ModBlocks.AVOCADO_SAPLING.get(), 0.65F);

        // 85% chance
        ComposterBlock.COMPOSTABLES.put(ModItems.POPCORN.get(), 0.85F);

        // 100% chance
        ComposterBlock.COMPOSTABLES.put(ModBlocks.AVOCADO_BUNDLE.get(), 0.65F);
    }

    //public static void registerAnimalFeeds() {
    //    Ingredient newChickenFood = Ingredient.of(ModItems.CUCUMBER_SEEDS.get(), ModItems.CORN_KERNELS.get(),
    //            ModItems.EGGPLANT_SEEDS.get(), ModItems.WHITE_EGGPLANT_SEEDS.get());
    //    Chicken.FOOD_ITEMS = new CompoundIngredient(Arrays.asList(Chicken.FOOD_ITEMS, newChickenFood))
    //    {
    //    };

    //    Ingredient newPigFood = Ingredient.of(ModItems.CUCUMBER.get(), ModItems.EGGPLANT.get(),
    //            ModItems.WHITE_EGGPLANT.get(), ModItems.CORN_COB.get());
    //    Pig.FOOD_ITEMS = new CompoundIngredient(Arrays.asList(Pig.FOOD_ITEMS, newPigFood))
    //    {
    //    };

    //    Collections.addAll(Parrot.TAME_FOOD, ModItems.CUCUMBER_SEEDS.get(), ModItems.CORN_KERNELS.get(),
    //            ModItems.EGGPLANT_SEEDS.get(), ModItems.WHITE_EGGPLANT_SEEDS.get());
    //}
}