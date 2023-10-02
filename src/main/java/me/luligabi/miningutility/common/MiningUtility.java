package me.luligabi.miningutility.common;

import eu.midnightdust.lib.config.MidnightConfig;
import me.luligabi.miningutility.common.registry.BlockRegistry;
import me.luligabi.miningutility.common.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class MiningUtility implements ModInitializer {
    public static final String MOD_ID = "miningutility";

    @Override
    public void onInitialize() {
        MidnightConfig.init(MOD_ID, ModConfig.class);
        BlockRegistry.init();
        ItemRegistry.init();
    }

    public static Identifier id(String identifier) {
        return new Identifier(MOD_ID, identifier);
    }
}
