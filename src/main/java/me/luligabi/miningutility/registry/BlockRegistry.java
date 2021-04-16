package me.luligabi.miningutility.registry;

import me.luligabi.miningutility.MiningUtility;
import me.luligabi.miningutility.block.InvertedRopeLadderBlock;
import me.luligabi.miningutility.block.RopeLadderBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(MiningUtility.MOD_ID, "rope_ladder"), ROPE_LADDER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MiningUtility.MOD_ID, "rope_ladder"), new BlockItem(ROPE_LADDER_BLOCK, new Item.Settings().group(MiningUtility.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MiningUtility.MOD_ID, "inverted_rope_ladder"), INVERTED_ROPE_LADDER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MiningUtility.MOD_ID, "inverted_rope_ladder"), new BlockItem(INVERTED_ROPE_LADDER_BLOCK, new Item.Settings().group(MiningUtility.ITEM_GROUP)));
    }

    public static final RopeLadderBlock ROPE_LADDER_BLOCK = new RopeLadderBlock(Block.Settings.of(Material.SUPPORTED).sounds(BlockSoundGroup.LADDER).nonOpaque());

    public static final InvertedRopeLadderBlock INVERTED_ROPE_LADDER_BLOCK = new InvertedRopeLadderBlock(Block.Settings.of(Material.SUPPORTED).sounds(BlockSoundGroup.LADDER).nonOpaque());
}
