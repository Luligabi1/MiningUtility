package me.luligabi.miningutility.common;

import me.luligabi.miningutility.common.block.BlockRegistry;
import me.luligabi.miningutility.common.item.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MiningUtility implements ModInitializer {

    SimpleConfig config = SimpleConfig.of(MOD_ID).provider(this::provider).request(); // TODO: Change to Omega Config on 1.19 port

    private String provider(String filename) {
        return "# Mining Utility Configuration File\n\n" +

                "# Expand block limit for the Rope Ladder. Bigger numbers might cause stutter on weaker PCs.\n" +
                "ropeLadderBlockLimit=64\n\n" +

                "# Expand block limit for the Inverted Rope Ladder. Bigger numbers might cause stutter on weaker PCs.\n" +
                "invertedRopeLadderBlockLimit=64\n\n" +

                "# Whether the Mining Helmet should be slowly damaged while on use\n" +
                "damageMiningHelmetOnUse=true";
    }

    @Override
    public void onInitialize() {
        BlockRegistry.init();
        ItemRegistry.init();
    }

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MiningUtility.MOD_ID, "item_group"))
            .icon(() -> new ItemStack(ItemRegistry.ESCAPE_ROPE_ITEM))
            .build();

    public static final String MOD_ID = "miningutility";

    public static Identifier modId(String identifier) {
        return new Identifier(MOD_ID, identifier);
    }

    public SimpleConfig getConfig() {
        return config;
    }
}
