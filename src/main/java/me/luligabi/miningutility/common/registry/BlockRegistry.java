package me.luligabi.miningutility.common.registry;

import me.luligabi.miningutility.common.MiningUtility;
import me.luligabi.miningutility.common.block.InvertedRopeLadderBlock;
import me.luligabi.miningutility.common.block.MiniTorchBlock;
import me.luligabi.miningutility.common.block.RopeLadderBlock;
import me.luligabi.miningutility.common.block.WallMiniTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockRegistry {

    public static void register() {
        Registry.register(Registry.BLOCK, new Identifier(MiningUtility.MOD_ID, "rope_ladder"), ROPE_LADDER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MiningUtility.MOD_ID, "rope_ladder"), new BlockItem(ROPE_LADDER_BLOCK, new Item.Settings().group(MiningUtility.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MiningUtility.MOD_ID, "inverted_rope_ladder"), INVERTED_ROPE_LADDER_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MiningUtility.MOD_ID, "inverted_rope_ladder"), new BlockItem(INVERTED_ROPE_LADDER_BLOCK, new Item.Settings().group(MiningUtility.ITEM_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier(MiningUtility.MOD_ID, "mini_torch"), MINI_TORCH_BLOCK);
        Registry.register(Registry.BLOCK, new Identifier(MiningUtility.MOD_ID, "wall_mini_torch"), WALL_MINI_TORCH_BLOCK);
    }

    public static final RopeLadderBlock ROPE_LADDER_BLOCK = new RopeLadderBlock(Block.Settings.of(Material.DECORATION).sounds(BlockSoundGroup.LADDER).nonOpaque());
    public static final InvertedRopeLadderBlock INVERTED_ROPE_LADDER_BLOCK = new InvertedRopeLadderBlock(Block.Settings.of(Material.DECORATION).sounds(BlockSoundGroup.LADDER).nonOpaque());

    public static final MiniTorchBlock MINI_TORCH_BLOCK = new MiniTorchBlock(Block.Settings.of(Material.DECORATION).sounds(BlockSoundGroup.LADDER).nonOpaque().noCollision().breakInstantly().luminance((state) -> 14).sounds(BlockSoundGroup.WOOD), ParticleTypes.FLAME);
    public static final WallMiniTorchBlock WALL_MINI_TORCH_BLOCK = new WallMiniTorchBlock(Block.Settings.of(Material.DECORATION).sounds(BlockSoundGroup.LADDER).nonOpaque().noCollision().breakInstantly().luminance((state) -> 14).sounds(BlockSoundGroup.WOOD), ParticleTypes.FLAME);

}