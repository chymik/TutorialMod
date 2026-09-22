package net.chymik.tutorialmod.item.custom;

import net.chymik.tutorialmod.attachment.ModAttachments;
import net.chymik.tutorialmod.saveddata.RegistreFruits;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FruitDuDemonItem extends Item {

    public FruitDuDemonItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && level instanceof ServerLevel serverLevel && entity instanceof Player player) {
            String fruitDejaMange = player.getAttached(ModAttachments.FRUIT_MANGE);

            if (fruitDejaMange != null) {
                // Deuxième fruit mangé -> incompatibilité fatale, pas d'effets appliqués.
                player.sendSystemMessage(Component.literal(
                        "Ton corps ne supporte pas un second Fruit du Démon..."
                ));
                player.kill(serverLevel);
                return stack;
            }

            String nomFruit = BuiltInRegistries.ITEM.getKey(this).getPath();
            RegistreFruits registre = RegistreFruits.get(serverLevel.getServer());

            if (!registre.estDisponible(nomFruit)) {
                // Fruit déjà réclamé par quelqu'un d'autre : aucun effet, juste consommé.
                player.sendSystemMessage(Component.literal(
                        "Ce fruit a déjà perdu son pouvoir, quelqu'un d'autre le possède déjà..."
                ));
                return stack;
            }

            registre.reclamerFruit(nomFruit);
            player.setAttached(ModAttachments.FRUIT_MANGE, nomFruit);
            player.sendSystemMessage(Component.literal(
                    "Tu sens un pouvoir immense t'envahir... mais la mer te rejette désormais."
            ));
        }

        // Laisse Minecraft appliquer les effets définis dans ModFoods
        // (ABSORPTION, HEALTH_BOOST, RESISTANCE, REGENERATION) via le Consumable —
        // seulement si on n'a pas déjà tué le joueur au-dessus.
        return super.finishUsingItem(stack, level, entity);
    }
}
