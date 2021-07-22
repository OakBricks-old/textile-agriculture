package org.oakbricks.textileagriculture;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.oakbricks.textileagriculture.init.ModBlocks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oakbricks.textileagriculture.init.ModItems;

public class TextileAgricultureMain implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("Textile Agriculture");

	public static String MOD_ID = "textile_agriculture";

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "main"),
			() -> new ItemStack(ModItems.HOLLY_BERRIES));



	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ModBlocks.registerBlocks();

		ModItems.registerItems();

		LOGGER.info("[Textile Agriculture]: Textile Agriculture has initialized");

	}
}
