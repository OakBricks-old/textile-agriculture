package oakbricks.textileagriculture;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import oakbricks.textileagriculture.init.ModBlocks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextileAgricultureMain implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("textile_agriculture");

	public static String MOD_ID = "textile_agriculture";

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "main"),
			() -> new ItemStack(Blocks.COBBLESTONE));

	public static final ConfiguredFeature<?, ?> AVOCADO_TREE = Feature.TREE
			// Configure the feature using the builder
			.configure(new TreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(ModBlocks.AVOCADO_LOG.getDefaultState()), // Trunk block provider
					new StraightTrunkPlacer(8, 3, 0), // places a straight trunk
					new SimpleBlockStateProvider(ModBlocks.AVOCADO_LEAVES.getDefaultState()), // Foliage block provider
					new SimpleBlockStateProvider(ModBlocks.AVOCADO_SAPLING.getDefaultState()), // Sapling provider; used to determine what blocks the tree can generate on
					new BlobFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), 3), // places leaves as a blob (radius, offset from trunk, height)
					new TwoLayersFeatureSize(1, 0, 1) // The width of the tree at different layers; used to see how tall the tree can be without clipping into blocks
			).build())
			.spreadHorizontally()
			.applyChance(3); // About a 33% chance to generate per chunk (1/x)

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		RegistryKey<ConfiguredFeature<?, ?>> treeRich = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MOD_ID, "avocado_tree"));

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, treeRich.getValue(), AVOCADO_TREE);

		// You should use the VEGETAL_DECORATION generation step for trees
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION, treeRich);

		LOGGER.info("hi");
	}
}
