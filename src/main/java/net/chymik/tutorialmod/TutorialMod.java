package net.chymik.tutorialmod;

import net.chymik.tutorialmod.attachment.ModAttachments;
import net.chymik.tutorialmod.block.ModBlocks;
import net.chymik.tutorialmod.creativemodtab.ModCreativeModeTabs;
import net.chymik.tutorialmod.event.ModEvents;
import net.chymik.tutorialmod.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModAttachments.registerModAttachments();
		ModEvents.registerModEvents();
	}
}
