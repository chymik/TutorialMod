package net.chymik.tutorialmod.food;

import net.chymik.tutorialmod.item.ModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.List;

import static net.minecraft.world.item.component.Consumables.defaultFood;

public class ModFoods {
    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25F).build();

    public static final Consumable CONSUMABLE_STRAWBERRY = Consumables.defaultFood()
            .consumeSeconds(1f).onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 200), 0.15f)).build();

    public static final FoodProperties FRUIT_DU_DEMON = new FoodProperties.Builder().nutrition(10).saturationModifier(0.5f).alwaysEdible().build();

    public static final Consumable CONSUMABLE_FRUIT_DU_DEMON = defaultFood()
            .consumeSeconds(10f).onConsume(
                    new ApplyStatusEffectsConsumeEffect(
                            List.of(
                                    new MobEffectInstance(MobEffects.ABSORPTION,1000, 4),
                                    new MobEffectInstance(MobEffects.HEALTH_BOOST,MobEffectInstance.INFINITE_DURATION,4,false,false,false),
                                    new MobEffectInstance(MobEffects.RESISTANCE,1000, 1),
                                    new MobEffectInstance(MobEffects.REGENERATION, 600, 1)
                            ),1F
                    )
            ).build();
}
