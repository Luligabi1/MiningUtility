package me.luligabi.miningutility.common.item.misc;

import dev.architectury.registry.registries.RegistrySupplier;
import me.luligabi.miningutility.common.MiningUtility;
import me.luligabi.miningutility.common.misc.SoundRegistry;
import me.luligabi.miningutility.common.misc.TagRegistry;
import net.minecraft.Util;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;

public class ArmorMaterialRegistry {


    public static final RegistrySupplier<ArmorMaterial> MINING_HELMET = MiningUtility.ARMOR_MATERIALS.register(
        MiningUtility.id("mining_helmet"),
        () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), (enumMap) -> {
                enumMap.put(ArmorItem.Type.BOOTS, 2);
                enumMap.put(ArmorItem.Type.LEGGINGS, 2);
                enumMap.put(ArmorItem.Type.CHESTPLATE, 2);
                enumMap.put(ArmorItem.Type.HELMET, 2);
                enumMap.put(ArmorItem.Type.BODY, 2);
            }),
            0,
            SoundRegistry.MINING_HELMET_EQUIP,
            () -> Ingredient.of(TagRegistry.COMMON_COAL_TORCHES),
            List.of(new ArmorMaterial.Layer(MiningUtility.id("mining_helmet"))),
            0F,
            0F
        )
    );


    public static void init() {
        // NO-OP
    }

    private ArmorMaterialRegistry() {
        // NO-OP
    }
}