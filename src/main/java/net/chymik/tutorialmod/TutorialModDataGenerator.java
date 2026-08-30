package net.chymik.tutorialmod;

import net.chymik.tutorialmod.datagen.ModBlockLootTableProvider;
import net.chymik.tutorialmod.datagen.ModBlockTagProvider;
import net.chymik.tutorialmod.datagen.ModModelProvider;
import net.chymik.tutorialmod.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TutorialModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);


	}
}
