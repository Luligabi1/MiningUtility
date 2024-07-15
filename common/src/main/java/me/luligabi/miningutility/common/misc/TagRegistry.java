package me.luligabi.miningutility.common.misc;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TagRegistry {

    public static final TagKey<Item> COMMON_COAL_TORCHES = TagKey.create(Registries.ITEM, ResourceLocation.tryBuild("c", "torches/coal"));


    public static void init() {
        // NO-OP
    }

    private TagRegistry() {
        // NO-OP
    }
}