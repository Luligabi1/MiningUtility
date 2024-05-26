package me.luligabi.miningutility.common.item.misc;

import dev.architectury.registry.registries.RegistrySupplier;
import me.luligabi.miningutility.common.MiningUtility;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

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
            SoundEvents.ARMOR_EQUIP_IRON, // FIXME
            () -> Ingredient.EMPTY, // FIXME
            List.of(new ArmorMaterial.Layer(MiningUtility.id("mining_helmet"))), // FIXME
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