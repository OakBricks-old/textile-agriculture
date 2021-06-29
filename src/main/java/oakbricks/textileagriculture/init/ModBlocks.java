package oakbricks.textileagriculture.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import oakbricks.textileagriculture.TextileAgricultureMain;
import oakbricks.textileagriculture.blocks.AvocadoLeafBlock;
import oakbricks.textileagriculture.blocks.AvocadoSaplingBlock;
import oakbricks.textileagriculture.util.AvocadoSaplingGenerator;

import static oakbricks.textileagriculture.TextileAgricultureMain.AVOCADO_TREE;

public class ModBlocks {

    public static final Block AVOCADO_LEAVES = new AvocadoLeafBlock(FabricBlockSettings.of(Material.LEAVES)
            .strength(0.2F)
            .ticksRandomly()
            .sounds(BlockSoundGroup.GRASS)
            .nonOpaque()
            .blockVision((state, world, pos) -> false)
            .suffocates((state, world, pos) -> false)
            .breakByTool(FabricToolTags.HOES));

    public static final Block AVOCADO_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD)
            .sounds(BlockSoundGroup.WOOD)
            .strength(2.0f));

    public static final Block AVOCADO_SAPLING = new AvocadoSaplingBlock(new AvocadoSaplingGenerator(AVOCADO_TREE), FabricBlockSettings.copyOf(Blocks.OAK_SAPLING));


    public static void registerBlocks() {

        //avocado stuff
        Registry.register(Registry.BLOCK, new Identifier(TextileAgricultureMain.MOD_ID, "avocado_leaves"), AVOCADO_LEAVES);
        Registry.register(Registry.BLOCK, new Identifier(TextileAgricultureMain.MOD_ID, "avocado_log"), AVOCADO_LOG);
        Registry.register(Registry.BLOCK, new Identifier(TextileAgricultureMain.MOD_ID, "avocado_sapling"), AVOCADO_SAPLING);

        //blockitems
        Registry.register(Registry.ITEM, new Identifier(TextileAgricultureMain.MOD_ID, "avocado_sapling"), new BlockItem(AVOCADO_SAPLING, new FabricItemSettings().group(TextileAgricultureMain.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(TextileAgricultureMain.MOD_ID, "avocado_log"), new BlockItem(AVOCADO_LOG, new FabricItemSettings().group(TextileAgricultureMain.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier(TextileAgricultureMain.MOD_ID, "avocado_leaves"), new BlockItem(AVOCADO_LEAVES, new FabricItemSettings().group(TextileAgricultureMain.ITEM_GROUP)));

    }

}