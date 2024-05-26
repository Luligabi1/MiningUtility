package me.luligabi.miningutility.client;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import me.luligabi.miningutility.common.block.BlockRegistry;
import net.minecraft.client.renderer.RenderType;

public class MiningUtilityClient {

    public static void init() {
        // Usual init is too late for neoforge
        ClientLifecycleEvent.CLIENT_SETUP.register(instance -> {
            RenderTypeRegistry.register(
                RenderType.cutout(),
                BlockRegistry.ROPE_LADDER.get(),
                BlockRegistry.INVERTED_ROPE_LADDER.get(),
                BlockRegistry.MINI_TORCH.get(),
                BlockRegistry.WALL_MINI_TORCH.get()
            );
        });
    }
}