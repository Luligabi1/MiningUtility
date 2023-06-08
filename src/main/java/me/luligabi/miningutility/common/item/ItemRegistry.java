package me.luligabi.miningutility.common.item;

import me.luligabi.miningutility.common.MiningUtility;
import me.luligabi.miningutility.common.armormaterial.MiningHelmetArmorMaterial;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ItemRegistry {


    public static Item ESCAPE_ROPE_ITEM = initItem("escape_rope", new EscapeRopeItem(new FabricItemSettings().maxDamage(16)));

    private static final ArmorMaterial MINING_HELMET_ARMOR_MATERIAL = new MiningHelmetArmorMaterial();
    public static final Item MINING_HELMET = initItem("mining_helmet", new ArmorItem(MINING_HELMET_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new FabricItemSettings().maxDamage(165)));


    private static Item initItem(String identifier, Item item) {
        return Registry.register(Registries.ITEM, MiningUtility.modId(identifier), item);
    }

    public static void init() {
        // NO-OP
    }

    private ItemRegistry() {
        // NO-OP
    }

}