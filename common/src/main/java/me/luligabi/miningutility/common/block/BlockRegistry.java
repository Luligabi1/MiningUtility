package me.luligabi.miningutility.common.block;

import dev.architectury.registry.registries.RegistrySupplier;
import me.luligabi.miningutility.common.MiningUtility;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;

public class BlockRegistry {



    public static final RegistrySupplier<RopeLadderBlock> ROPE_LADDER = MiningUtility.BLOCKS.register(
        MiningUtility.id("rope_ladder"),
        () -> new RopeLadderBlock(Direction.DOWN, 64)
    );
    public static final RegistrySupplier<Item> ROPE_LADDER_ITEM = MiningUtility.ITEMS.register(
        MiningUtility.id("rope_ladder"),
        () -> new BlockItem(ROPE_LADDER.get(), new Item.Properties().arch$tab(MiningUtility.ITEM_GROUP))
    );

    public static final RegistrySupplier<RopeLadderBlock> INVERTED_ROPE_LADDER = MiningUtility.BLOCKS.register(
        MiningUtility.id("inverted_rope_ladder"),
        () -> new RopeLadderBlock(Direction.UP, 64)
    );
    public static final RegistrySupplier<Item> INVERTED_ROPE_LADDER_ITEM = MiningUtility.ITEMS.register(
        MiningUtility.id("inverted_rope_ladder"),
        () -> new BlockItem(INVERTED_ROPE_LADDER.get(), new Item.Properties().arch$tab(MiningUtility.ITEM_GROUP))
    );

    public static final RegistrySupplier<MiniTorchBlock> MINI_TORCH = MiningUtility.BLOCKS.register(
        MiningUtility.id("mini_torch"),
        MiniTorchBlock::new
    );
    public static final RegistrySupplier<WallMiniTorchBlock> WALL_MINI_TORCH = MiningUtility.BLOCKS.register(
        MiningUtility.id("wall_mini_torch"),
        WallMiniTorchBlock::new
    );
    public static final RegistrySupplier<Item> MINI_TORCH_ITEM = MiningUtility.ITEMS.register(
        MiningUtility.id("mini_torch"),
        () -> new StandingAndWallBlockItem(MINI_TORCH.get(), WALL_MINI_TORCH.get(), new Item.Properties().arch$tab(MiningUtility.ITEM_GROUP), Direction.UP)
    );


    public static void init() {
        // NO-OP
    }

    private BlockRegistry() {
        // NO-OP
    }
}