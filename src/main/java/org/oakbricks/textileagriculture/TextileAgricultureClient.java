package org.oakbricks.textileagriculture;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import org.oakbricks.textileagriculture.init.ModBlocks;

public class TextileAgricultureClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) ->
                view != null && pos != null
                    ? BiomeColors.getFoliageColor(view, pos)
                    : FoliageColors.getDefaultColor(), ModBlocks.AVOCADO_LEAVES);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 0
                ? FoliageColors.getDefaultColor()
                : FoliageColors.getDefaultColor(), ModBlocks.AVOCADO_LEAVES.asItem());


        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.HOLLY_BERRY_BUSH, RenderLayer.getCutoutMipped());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.AVOCADO_SAPLING, RenderLayer.getCutoutMipped());

    }
}
