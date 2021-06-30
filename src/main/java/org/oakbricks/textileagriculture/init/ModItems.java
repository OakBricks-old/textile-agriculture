package org.oakbricks.textileagriculture.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.oakbricks.textileagriculture.TextileAgricultureMain;

import static org.oakbricks.textileagriculture.TextileAgricultureMain.MOD_ID;

public class ModItems {

    public static final Item AVOCADO = new Item(new FabricItemSettings().group(TextileAgricultureMain.ITEM_GROUP).recipeRemainder(ModItems.AVOCADO_PIT));

    public static final Item AVOCADO_SLICE = new Item(new FabricItemSettings().group(TextileAgricultureMain.ITEM_GROUP).food(new FoodComponent.Builder().hunger(4).saturationModifier(5).build()));

    public static final Item AVOCADO_PIT = new AliasedBlockItem(ModBlocks.AVOCADO_SAPLING, new Item.Settings().group(ItemGroup.MISC));

    public static final Item HOLLY_BERRIES = new AliasedBlockItem(ModBlocks.HOLLY_BERRY_BUSH, new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(3).saturationModifier(4).statusEffect(new StatusEffectInstance(StatusEffects.POISON,100), 1).build()));

    public static void registerItems() {

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "avocado"), AVOCADO);

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "avocado_slice"), AVOCADO_SLICE);

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "avocado_pit"), AVOCADO_PIT);

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "holly_berries"), HOLLY_BERRIES);

    }

}
