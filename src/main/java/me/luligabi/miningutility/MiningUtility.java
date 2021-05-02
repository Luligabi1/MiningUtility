package me.luligabi.miningutility;

import me.luligabi.miningutility.registry.BlockRegistry;
import me.luligabi.miningutility.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class MiningUtility implements ModInitializer {

    public static final String MOD_ID = "miningutility";

    SimpleConfig config = SimpleConfig.of(MOD_ID).provider(this::provider).request();

    private String provider(String filename) {
        return "# Mining Utility Configuration File\n\n" +

                "# Expand block limit for the Rope Ladder. Bigger numbers might cause stutter on weaker PCs.\n" +
                "ropeLadderBlockLimit=64\n\n" +

                "# Expand block limit for the Inverted Rope Ladder. Bigger numbers might cause stutter on weaker PCs.\n" +
                "invertedRopeLadderBlockLimit=64";
    }
    
    @Override
    public void onInitialize() {
        ItemRegistry.register();
        BlockRegistry.register();
    }

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(
            new Identifier(MOD_ID, "item_group"))
            .icon(() -> new ItemStack(ItemRegistry.ESCAPE_ROPE_ITEM))
            .build();

    public SimpleConfig getConfig() {
        return config;
    }
}
