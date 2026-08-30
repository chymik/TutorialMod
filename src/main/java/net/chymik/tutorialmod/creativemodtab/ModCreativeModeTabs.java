package net.chymik.tutorialmod.creativemodtab;

import net.chymik.tutorialmod.TutorialMod;
import net.chymik.tutorialmod.TutorialModDataGenerator;
import net.chymik.tutorialmod.block.ModBlocks;
import net.chymik.tutorialmod.item.ModItems;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {

    public static final CreativeModeTab FLUORITE_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "fluorite_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.FLUORITE))
                    .title(Component.translatable("creativemodetab.tutorialmod.fluorite_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.FLUORITE);
                        output.accept(ModItems.RAW_FLUORITE);
                        output.accept(ModItems.CHISEL);
                        output.accept(ModItems.STRAWBERRY);
                    }).build());

    public static final CreativeModeTab ALL_BLUE_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "all_blue_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GOMU_GOMU_NO_MI))
                    .title(Component.translatable("creativemodetab.tutorialmod.fluorite_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.GOMU_GOMU_NO_MI);
                    }).build());

    public static final CreativeModeTab ALL_BLUE_BLOCKS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "all_blue_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.GRANIT_MARIN))
                    .title(Component.translatable("creativemodetab.tutorialmod.fluorite_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.GRANIT_MARIN);
                    }).build());

    public static final CreativeModeTab FLUORITE_BLOCK_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "fluorite_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.FLUORITE_BLOCK))
                    .title(Component.translatable("creativemodetab.tutorialmod.fluorite_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.FLUORITE_BLOCK);
                        output.accept(ModBlocks.RAW_FLUORITE_BLOCK);
                        output.accept(ModBlocks.FLUORITE_ORE);
                        output.accept(ModBlocks.FLUORITE_DEEPSLATE_ORE);

                        output.accept(ModBlocks.FLUORITE_NETHER_ORE);
                        output.accept(ModBlocks.FLUORITE_END_ORE);


                    }).build());

    public static void registerModCreativeModeTabs() {
        TutorialMod.LOGGER.info("Registering Creative Mode Tabs for " + TutorialMod.MOD_ID);


    }
}
