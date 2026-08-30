package net.chymik.tutorialmod.attachment;

import net.chymik.tutorialmod.TutorialMod;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.resources.Identifier;
import com.mojang.serialization.Codec;

public class ModAttachments {
    public static final AttachmentType<Boolean> A_MANGE_UN_FRUIT = AttachmentRegistry.create(
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "a_mange_un_fruit"),
            builder -> builder
                    .initializer(() -> false)
                    .persistent(Codec.BOOL)
    );

    public static void registerModAttachments() {
        TutorialMod.LOGGER.info("Registering Mod Attachments for " + TutorialMod.MOD_ID);
    }
}