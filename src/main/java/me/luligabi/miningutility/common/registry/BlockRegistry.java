package me.luligabi.miningutility.common.registry;

import me.luligabi.miningutility.common.MiningUtility;
import me.luligabi.miningutility.common.block.InvertedRopeLadderBlock;
import me.luligabi.miningutility.common.block.MiniTorchBlock;
import me.luligabi.miningutility.common.block.RopeLadderBlock;
import me.luligabi.miningutility.common.block.WallMiniTorchBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public interface BlockRegistry {
    Map<Block, Identifier> BLOCKS = new LinkedHashMap<>();

    Block ROPE_LADDER_BLOCK = createBlock("rope_ladder", new RopeLadderBlock());
    Block INVERTED_ROPE_LADDER_BLOCK = createBlock("inverted_rope_ladder", new InvertedRopeLadderBlock());
    Block MINI_TORCH_BLOCK = createBlock("mini_torch", new MiniTorchBlock(), null);
    Block WALL_MINI_TORCH_BLOCK = createBlock("wall_mini_torch", new WallMiniTorchBlock(), null);

    static void init() {
        BLOCKS.keySet().forEach(block -> Registry.register(Registries.BLOCK, BLOCKS.get(block), block));
    }

    static Block createBlock(String name, Block block) {
        return createBlock(name, block, new BlockItem(block, new FabricItemSettings()));
    }

    static Block createBlock(String name, Block block, @Nullable BlockItem blockItem) {
        BLOCKS.put(block, MiningUtility.id(name));
        if (blockItem != null) ItemRegistry.createItem(name, blockItem);
        return block;
    }
}