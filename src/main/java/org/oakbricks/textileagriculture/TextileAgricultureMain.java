package org.oakbricks.textileagriculture;

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
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.oakbricks.textileagriculture.init.ModBlocks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oakbricks.textileagriculture.init.ModItems;
import org.oakbricks.textileagriculture.mixin.FoliagePlacerTypeInvoker;
import org.oakbricks.textileagriculture.mixin.TreeDecoratorTypeInvoker;
import org.oakbricks.textileagriculture.mixin.TrunkPlacerTypeInvoker;
import org.oakbricks.textileagriculture.util.AvocadoFoliagePlacer;
import org.oakbricks.textileagriculture.util.AvocadoTreeDecorator;
import org.oakbricks.textileagriculture.util.AvocadoTrunkPlacer;
import org.oakbricks.textileagriculture.worldgen.StoneSpiralFeature;

import java.util.Collections;

public class TextileAgricultureMain implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("Textile Agriculture");

	public static String MOD_ID = "textile_agriculture";

	public static final TrunkPlacerType<AvocadoTrunkPlacer> AVOCADO_TRUNK_PLACER = TrunkPlacerTypeInvoker.callRegister("avocado_trunk_placer", AvocadoTrunkPlacer.CODEC);

	public static final FoliagePlacerType<AvocadoFoliagePlacer> AVOCADO_FOLIAGE_PLACER = FoliagePlacerTypeInvoker.callRegister("avocado_foliage_placer", AvocadoFoliagePlacer.CODEC);

	public static final TreeDecoratorType<AvocadoTreeDecorator> AVOCADO_TREE_DECORATOR = TreeDecoratorTypeInvoker.callRegister("avocado_tree_decorator", AvocadoTreeDecorator.CODEC);

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "main"),
			() -> new ItemStack(ModBlocks.AVOCADO_SAPLING));

	public static final ConfiguredFeature<?, ?> AVOCADO_TREE = Feature.TREE
			// Configure the feature using the builder
			.configure(new TreeFeatureConfig.Builder(
					new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()), // Trunk block provider
					new AvocadoTrunkPlacer(6, 2, 0), // places a straight trunk
					new SimpleBlockStateProvider(ModBlocks.AVOCADO_LEAVES.getDefaultState()), // Foliage block provider
					new SimpleBlockStateProvider(ModBlocks.AVOCADO_SAPLING.getDefaultState()), // Sapling provider; used to determine what blocks the tree can generate on
					new AvocadoFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(1), ConstantIntProvider.create(3)), // places leaves as a blob (radius, offset from trunk, height)
					new TwoLayersFeatureSize(1, 0, 1) // The width of the tree at different layers; used to see how tall the tree can be without clipping into blocks
			).decorators(Collections.singletonList(AvocadoTreeDecorator.INSTANCE)).ignoreVines().forceDirt().build())
			.spreadHorizontally()
			.applyChance(3); // About a 33% chance to generate per chunk (1/x)

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


		RegistryKey<ConfiguredFeature<?, ?>> avocadoTree = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier(MOD_ID, "avocado_tree"));

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, avocadoTree.getValue(), AVOCADO_TREE);

		// You should use the VEGETAL_DECORATION generation step for trees
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION, avocadoTree);

		ModBlocks.registerBlocks();

		ModItems.registerItems();

		LOGGER.info("Textile Agriculture has initialized");
	}
}
