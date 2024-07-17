package me.luligabi.miningutility.neoforge.client;

import dev.architectury.event.events.client.ClientLifecycleEvent;
import dev.architectury.registry.client.rendering.RenderTypeRegistry;
import me.luligabi.miningutility.client.MiningUtilityClient;
import me.luligabi.miningutility.common.MiningUtility;
import me.luligabi.miningutility.common.block.BlockRegistry;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = MiningUtility.MOD_ID, dist = Dist.CLIENT)
public class MiningUtilityClientNeoforge {

    public MiningUtilityClientNeoforge() {
        ClientLifecycleEvent.CLIENT_SETUP.register(instance -> {
            MiningUtilityClient.init();
        });
    }
}