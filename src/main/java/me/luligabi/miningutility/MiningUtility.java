package me.luligabi.miningutility;

import me.luligabi.miningutility.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.kyrptonaught.customportalapi.portal.PortalIgnitionSource;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class MiningUtility implements ModInitializer {

    public static final String MOD_ID = "miningutility";

    @Override
    public void onInitialize() {
        ItemRegistry.register();
        // V WIP Blocks, items and colors.
        CustomPortalApiRegistry.addPortal(Blocks.CHISELED_STONE_BRICKS, PortalIgnitionSource.ItemUseSource(Items.TORCH), new Identifier(MOD_ID, "mining_dimension"), 51, 52, 49);
    }

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "item_group"))
            .icon(() -> new ItemStack(Items.IRON_PICKAXE)) //TODO: Change ItemGroup's icon.
            .build();
}
