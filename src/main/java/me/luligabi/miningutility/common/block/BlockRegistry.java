package me.luligabi.miningutility.common.block;

import me.luligabi.miningutility.common.MiningUtility;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class BlockRegistry {

    public static void init() {
        Registry.register(Registries.BLOCK, new Identifier(MiningUtility.MOD_ID, "rope_ladder"), ROPE_LADDER_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(MiningUtility.MOD_ID, "rope_ladder"), new BlockItem(ROPE_LADDER_BLOCK, new Item.Settings()));

        Registry.register(Registries.BLOCK, new Identifier(MiningUtility.MOD_ID, "inverted_rope_ladder"), INVERTED_ROPE_LADDER_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(MiningUtility.MOD_ID, "inverted_rope_ladder"), new BlockItem(INVERTED_ROPE_LADDER_BLOCK, new Item.Settings()));

        Registry.register(Registries.BLOCK, MiningUtility.modId("mini_torch"), MINI_TORCH_BLOCK);
        Registry.register(Registries.BLOCK, MiningUtility.modId("wall_mini_torch"), WALL_MINI_TORCH_BLOCK);
        Registry.register(Registries.ITEM, MiningUtility.modId("mini_torch"), new VerticallyAttachableBlockItem(MINI_TORCH_BLOCK, WALL_MINI_TORCH_BLOCK, new FabricItemSettings(), Direction.DOWN));
    }

    public static final RopeLadderBlock ROPE_LADDER_BLOCK = new RopeLadderBlock();
    public static final InvertedRopeLadderBlock INVERTED_ROPE_LADDER_BLOCK = new InvertedRopeLadderBlock();

    public static final MiniTorchBlock MINI_TORCH_BLOCK = new MiniTorchBlock(Block.Settings.of(Material.DECORATION).sounds(BlockSoundGroup.LADDER).nonOpaque().noCollision().breakInstantly().luminance((state) -> 14).sounds(BlockSoundGroup.WOOD));
    public static final WallMiniTorchBlock WALL_MINI_TORCH_BLOCK = new WallMiniTorchBlock(Block.Settings.of(Material.DECORATION).sounds(BlockSoundGroup.LADDER).nonOpaque().noCollision().breakInstantly().luminance((state) -> 14).sounds(BlockSoundGroup.WOOD));

}