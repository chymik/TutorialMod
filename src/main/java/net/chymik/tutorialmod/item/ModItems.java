package net.chymik.tutorialmod.item;

import net.chymik.tutorialmod.TutorialMod;
import net.chymik.tutorialmod.food.ModFoods;
import net.chymik.tutorialmod.item.custom.ChiselItem;
import net.chymik.tutorialmod.item.custom.FruitDuDemonItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.function.Function;

public class ModItems {

    public static final Item FLUORITE = registerItem("fluorite", Item::new);
    public static final Item RAW_FLUORITE = registerItem("raw_fluorite", Item::new);

    public static final Item CHISEL = registerItem("chisel_item", properties -> new ChiselItem(properties.durability(32)));

    public static final Item STRAWBERRY = registerItem("strawberry", properties -> new Item(properties
            .food(ModFoods.STRAWBERRY, ModFoods.CONSUMABLE_STRAWBERRY)));
    public static final Item GOMU_GOMU_NO_MI = registerItem("gomu_gomu_no_mi", properties -> new FruitDuDemonItem(properties
            .food(ModFoods.FRUIT_DU_DEMON, ModFoods.CONSUMABLE_FRUIT_DU_DEMON).rarity(Rarity.EPIC)));




    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name)))));
    }


    public static void registerModItems() {
    TutorialMod.LOGGER.info("Registering Items for " + TutorialMod.MOD_ID);

        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register(output -> {
            output.accept(FLUORITE);
            output.accept(RAW_FLUORITE);
        });

    }
}
