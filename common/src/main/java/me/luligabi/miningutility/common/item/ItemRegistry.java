package me.luligabi.miningutility.common.item;

import dev.architectury.registry.registries.RegistrySupplier;
import me.luligabi.miningutility.common.MiningUtility;

public class ItemRegistry {

    public static final RegistrySupplier<EscapeRopeItem> ESCAPE_ROPE = MiningUtility.ITEMS.register(
        MiningUtility.id("escape_rope"),
        EscapeRopeItem::new
    );

    public static final RegistrySupplier<EnderEscapeRopeItem> ENDER_ESCAPE_ROPE = MiningUtility.ITEMS.register(
        MiningUtility.id("ender_escape_rope"),
        EnderEscapeRopeItem::new
    );

    public static final RegistrySupplier<MiningHelmetItem> MINING_HELMET = MiningUtility.ITEMS.register(
        MiningUtility.id("mining_helmet"),
        MiningHelmetItem::new
    );

    public static void init() {
        // NO-OP
    }

    private ItemRegistry() {
        // NO-OP
    }
}