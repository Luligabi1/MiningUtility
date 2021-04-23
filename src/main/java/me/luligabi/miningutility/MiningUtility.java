package me.luligabi.miningutility;

import me.luligabi.miningutility.registry.BlockRegistry;
import me.luligabi.miningutility.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class MiningUtility implements ModInitializer {

    public static final String MOD_ID = "miningutility";

    @Override
    public void onInitialize() {
        ItemRegistry.register();
        BlockRegistry.register();
    }

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "item_group"))
            .icon(() -> new ItemStack(Items.IRON_PICKAXE)) //TODO: Change ItemGroup's icon.
            .build();
}
