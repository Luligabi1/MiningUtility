package me.luligabi.miningutility.common.registry;

import me.luligabi.miningutility.common.MiningUtility;
import me.luligabi.miningutility.common.armormaterial.MiningHelmetArmorMaterial;
import me.luligabi.miningutility.common.item.EscapeRopeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.LinkedHashMap;
import java.util.Map;

public interface ItemRegistry {
    Map<Item, Identifier> ITEMS = new LinkedHashMap<>();
    ItemGroup ITEM_GROUP = FabricItemGroup.builder().displayName(Text.translatable("itemGroup.miningutility.item_group")).icon(() -> new ItemStack(ItemRegistry.ESCAPE_ROPE_ITEM)).build();

    Item ESCAPE_ROPE_ITEM = createItem("escape_rope", new EscapeRopeItem(new FabricItemSettings().maxDamage(16)));
    Item MINING_HELMET = createItem("mining_helmet", new ArmorItem(MiningHelmetArmorMaterial.INSTANCE, ArmorItem.Type.HELMET, new FabricItemSettings().maxDamage(165)));
    Item MINI_TORCH = createItem("mini_torch", new VerticallyAttachableBlockItem(BlockRegistry.MINI_TORCH_BLOCK, BlockRegistry.WALL_MINI_TORCH_BLOCK, new FabricItemSettings(), Direction.DOWN));

    static void init() {
        Registry.register(Registries.ITEM_GROUP, MiningUtility.id("item_group"), ITEM_GROUP);
        ITEMS.keySet().forEach(item -> Registry.register(Registries.ITEM, ITEMS.get(item), item));
        Registries.ITEM_GROUP.getKey(ITEM_GROUP).ifPresent(key -> ITEMS.forEach((item, id) -> ItemGroupEvents.modifyEntriesEvent(key).register(content -> content.add(item))));
    }

    static <T extends Item> T createItem(String name, T item) {
        ITEMS.put(item, MiningUtility.id(name));
        return item;
    }
}