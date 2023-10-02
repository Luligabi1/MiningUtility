package me.luligabi.miningutility.client;

import me.luligabi.miningutility.common.registry.BlockRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class MiningUtilityClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), BlockRegistry.ROPE_LADDER_BLOCK, BlockRegistry.INVERTED_ROPE_LADDER_BLOCK, BlockRegistry.MINI_TORCH_BLOCK, BlockRegistry.WALL_MINI_TORCH_BLOCK);
    }
}