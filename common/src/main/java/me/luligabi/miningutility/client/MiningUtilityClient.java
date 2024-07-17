package me.luligabi.miningutility.client;

import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import me.luligabi.miningutility.common.block.BlockRegistry;
import net.minecraft.client.renderer.RenderType;

public class MiningUtilityClient {

    public static void init() {
        RenderTypeRegistry.register(
            RenderType.cutout(),
            BlockRegistry.ROPE_LADDER.get(),
            BlockRegistry.INVERTED_ROPE_LADDER.get(),
            BlockRegistry.MINI_TORCH.get(),
            BlockRegistry.WALL_MINI_TORCH.get()
        );
    }
}