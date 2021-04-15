package me.luligabi.miningutility.registry;

import me.luligabi.miningutility.MiningUtility;
import me.luligabi.miningutility.item.EscapeRopeItem;
import me.luligabi.miningutility.item.TunnelExcavatorItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(MiningUtility.MOD_ID, "escape_rope"), ESCAPE_ROPE_ITEM);
        Registry.register(Registry.ITEM, new Identifier(MiningUtility.MOD_ID, "tunnel_excavator"), TUNNEL_EXCAVATOR_ITEM);
    }

    public static EscapeRopeItem ESCAPE_ROPE_ITEM = new EscapeRopeItem(new FabricItemSettings().group(MiningUtility.ITEM_GROUP).maxDamage(16));

    public static TunnelExcavatorItem TUNNEL_EXCAVATOR_ITEM = new TunnelExcavatorItem(new FabricItemSettings().group(MiningUtility.ITEM_GROUP).maxCount(16));
}
