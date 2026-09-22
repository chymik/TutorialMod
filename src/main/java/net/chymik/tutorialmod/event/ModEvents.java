package net.chymik.tutorialmod.event;

import net.chymik.tutorialmod.TutorialMod;
import net.chymik.tutorialmod.attachment.ModAttachments;
import net.chymik.tutorialmod.saveddata.RegistreFruits;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.phys.Vec3;

public class ModEvents {

    public static void registerModEvents() {
        TutorialMod.LOGGER.info("Registering Mod Events for " + TutorialMod.MOD_ID);

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                String fruitMange = player.getAttached(ModAttachments.FRUIT_MANGE);

                if (fruitMange != null && player.isInWater()) {
                    // Durée courte (60 ticks = 3s), réappliquée à chaque tick tant que
                    // la condition est vraie -> reste actif en continu dans l'eau,
                    // et s'estompe naturellement quelques secondes après en être sorti.
                    // Utile pour l'affichage (icônes, faiblesse au combat, minage plus lent),
                    // mais Lenteur seule n'affecte PAS la vitesse de nage : voir plus bas.
                    player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60, 1, false, true, true));
                    player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, 0, false, true, true));
                    player.addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 60, 0, false, true, true));

                    // La nage n'est pas pilotée par les effets de statut : on écrase donc
                    // directement le vecteur de mouvement du joueur, tick par tick.
                    // Horizontal quasi à l'arrêt (10% de la vitesse voulue), et une légère
                    // poussée vers le bas pour simuler le fait qu'il coule.
                    Vec3 mouvement = player.getDeltaMovement();
                    double nouveauY = Math.min(mouvement.y, -0.1);
                    player.setDeltaMovement(mouvement.x * 0, nouveauY, mouvement.z * 0);
                    player.hurtMarked = true; // force la resynchronisation côté client
                }
            }
        });

        // Un joueur qui meurt avec un fruit ne le porte plus : le registre doit
        // le remarquer "disponible" pour que /fdd list et un futur mangeur ne
        // restent pas bloqués sur un fruit fantôme. On ne touche pas à l'attache
        // ni aux effets ici : la mort les efface déjà toute seule côté vanilla
        // (et l'attache n'a pas .copyOnDeath(), donc elle ne survit pas non plus).
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            if (entity instanceof ServerPlayer player) {
                String fruitMange = player.getAttached(ModAttachments.FRUIT_MANGE);
                if (fruitMange != null) {
                    // Entity n'a pas de getServer() dans cette version : on passe par
                    // le ServerLevel du joueur, comme déjà fait dans FruitDuDemonItem.
                    RegistreFruits registre = RegistreFruits.get(player.level().getServer());
                    registre.reinitialiserFruit(fruitMange);
                    TutorialMod.LOGGER.info("{} est mort en portant le fruit '{}' : le fruit redevient disponible.",
                            player.getName().getString(), fruitMange);
                }
            }
        });
    }
}
