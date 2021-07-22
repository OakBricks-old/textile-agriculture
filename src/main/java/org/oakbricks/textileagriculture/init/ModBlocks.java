package org.oakbricks.textileagriculture.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.oakbricks.textileagriculture.TextileAgricultureMain;
import org.oakbricks.textileagriculture.blocks.MetalPlate;
import org.oakbricks.textileagriculture.blocks.berries.HollyBerryBush;

import java.util.function.ToIntFunction;

public class ModBlocks {

    public static final Block HOLLY_BERRY_BUSH = new HollyBerryBush(FabricBlockSettings.of(Material.PLANT).ticksRandomly().noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH));

    public static final Block METAL_PLATE = new MetalPlate(FabricBlockSettings.of(Material.METAL).requiresTool().strength(5.0f, 6.5f));

    public static void registerBlocks() {

        //block stuff
        Registry.register(Registry.BLOCK, new Identifier(TextileAgricultureMain.MOD_ID, "holly_berry_bush"), HOLLY_BERRY_BUSH);

        Registry.register(Registry.ITEM, new Identifier(TextileAgricultureMain.MOD_ID, "holly_berry_bush"), new BlockItem(HOLLY_BERRY_BUSH, new FabricItemSettings().group(TextileAgricultureMain.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(TextileAgricultureMain.MOD_ID, "metal_plate"), METAL_PLATE);

        Registry.register(Registry.ITEM, new Identifier(TextileAgricultureMain.MOD_ID, "metal_plate"), new BlockItem(METAL_PLATE, new FabricItemSettings().group(TextileAgricultureMain.ITEM_GROUP)));

    }

}
