package me.luligabi.miningutility.common.item.misc;

import dev.architectury.registry.registries.RegistrySupplier;
import me.luligabi.miningutility.common.MiningUtility;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.level.dimension.DimensionType;

public class DataComponentRegistry {


    public static final RegistrySupplier<DataComponentType<BlockPos>> BLOCK_POS = MiningUtility.COMPONENTS.register(
        MiningUtility.id("block_pos"),
        () -> DataComponentType.<BlockPos>builder()
            .persistent(BlockPos.CODEC)
            //.networkSynchronized(BlockPos.STREAM_CODEC)
            .build()
    );

    public static final RegistrySupplier<DataComponentType<Holder<DimensionType>>> DIMENSION = MiningUtility.COMPONENTS.register(
        MiningUtility.id("dimension"),
        () -> DataComponentType.<Holder<DimensionType>>builder()
            .persistent(DimensionType.CODEC)
            .build()
    );

    public static void init() {
        // NO-OP
    }

    private DataComponentRegistry() {
        // NO-OP
    }

}