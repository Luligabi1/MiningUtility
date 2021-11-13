package me.luligabi.miningutility.common.item;

import me.luligabi.miningutility.common.MiningUtility;
import me.luligabi.miningutility.common.armormaterial.MiningHelmetArmorMaterial;
import me.luligabi.miningutility.common.block.BlockRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(MiningUtility.MOD_ID, "escape_rope"), ESCAPE_ROPE_ITEM);
        Registry.register(Registry.ITEM, new Identifier(MiningUtility.MOD_ID, "mini_torch"), MINI_TORCH);
        Registry.register(Registry.ITEM, new Identifier(MiningUtility.MOD_ID, "mining_helmet"), MINING_HELMET);
    }

    public static EscapeRopeItem ESCAPE_ROPE_ITEM = new EscapeRopeItem(new FabricItemSettings().group(MiningUtility.ITEM_GROUP).maxDamage(16));

    public static BlockItem MINI_TORCH = new WallStandingBlockItem(BlockRegistry.MINI_TORCH_BLOCK, BlockRegistry.WALL_MINI_TORCH_BLOCK, new FabricItemSettings().group(MiningUtility.ITEM_GROUP));

    public static final ArmorMaterial MINING_HELMET_ARMOR_MATERIAL = new MiningHelmetArmorMaterial();
    public static final Item MINING_HELMET = new ArmorItem(MINING_HELMET_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(MiningUtility.ITEM_GROUP).maxDamage(165));
}
