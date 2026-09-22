package net.chymik.tutorialmod.attachment;

import net.chymik.tutorialmod.TutorialMod;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;
import com.mojang.serialization.Codec;

public class ModAttachments {
    // Stocke le NOM du fruit mangé (ex: "gomu_gomu_no_mi"), pas juste un booléen.
    // C'est ce qui manquait : avec un simple true/false, impossible de savoir QUEL
    // fruit un joueur porte, donc impossible de retrouver "qui a le fruit X" pour
    // /fdd reset ou pour le libérer à la mort. Absent = null = n'a mangé aucun fruit.
    public static final AttachmentType<String> FRUIT_MANGE = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "fruit_mange"),
            builder -> builder
                    .initializer(() -> null)
                    .persistent(Codec.STRING)
            // toujours pas de .copyOnDeath() : la mort efface l'attache, exprès.
    );

    public static void registerModAttachments() {
        TutorialMod.LOGGER.info("Registering Mod Attachments for " + TutorialMod.MOD_ID);
    }
}