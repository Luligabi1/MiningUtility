package me.luligabi.miningutility.registry;

import me.luligabi.miningutility.MiningUtility;
import me.luligabi.miningutility.item.EscapeRopeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(MiningUtility.MOD_ID, "escape_rope"), ESCAPE_ROPE_ITEM);
    }

    public static EscapeRopeItem ESCAPE_ROPE_ITEM = new EscapeRopeItem(new FabricItemSettings().group(MiningUtility.ITEM_GROUP).maxDamage(16));

}
