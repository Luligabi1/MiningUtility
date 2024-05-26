package me.luligabi.miningutility.common;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import dev.architectury.registry.registries.RegistrySupplier;
import me.luligabi.miningutility.common.block.BlockRegistry;
import me.luligabi.miningutility.common.item.misc.ArmorMaterialRegistry;
import me.luligabi.miningutility.common.item.misc.DataComponentRegistry;
import me.luligabi.miningutility.common.item.ItemRegistry;
import me.luligabi.miningutility.common.misc.SoundRegistry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

public final class MiningUtility {
    
    public static void init() {
        DataComponentRegistry.init();
        ArmorMaterialRegistry.init();
        ItemRegistry.init();

        BlockRegistry.init();

        SoundRegistry.init();
    }

    public static ResourceLocation id(String id) {
        return new ResourceLocation(MOD_ID, id);
    }

    public static final String MOD_ID = "miningutility";
    public static final RegistrySupplier<CreativeModeTab> ITEM_GROUP;

    public static final Registrar<Block> BLOCKS;
    public static final Registrar<Item> ITEMS;
    public static final Registrar<DataComponentType<?>> COMPONENTS;
    public static final Registrar<ArmorMaterial> ARMOR_MATERIALS;
    public static final Registrar<SoundEvent> SOUND_EVENTS;
    public static final Registrar<CreativeModeTab> CREATIVE_TABS;
    private static final Supplier<RegistrarManager> MANAGER;

    static {
        MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MiningUtility.MOD_ID));
        BLOCKS = MiningUtility.MANAGER.get().get(Registries.BLOCK);
        ITEMS = MiningUtility.MANAGER.get().get(Registries.ITEM);
        COMPONENTS = MiningUtility.MANAGER.get().get(Registries.DATA_COMPONENT_TYPE);
        ARMOR_MATERIALS = MiningUtility.MANAGER.get().get(Registries.ARMOR_MATERIAL);
        SOUND_EVENTS = MiningUtility.MANAGER.get().get(Registries.SOUND_EVENT);
        CREATIVE_TABS = MiningUtility.MANAGER.get().get(Registries.CREATIVE_MODE_TAB);

        ITEM_GROUP = CREATIVE_TABS.register(
            id("item_group"),
            () -> CreativeTabRegistry.create(
                Component.literal("Mining Utility"),
                () -> new ItemStack(ItemRegistry.ESCAPE_ROPE.get())
            )
        );
    }

}