package me.luligabi.miningutility.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RopeLadderBlock extends LadderBlock {

    public RopeLadderBlock(Settings settings) {
        super(AbstractBlock.Settings.copy(Blocks.LADDER));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(!world.isClient()) {
            BlockPos currentBellowPos = pos.down();
            Block currentBellow = world.getBlockState(currentBellowPos).getBlock();
            while(currentBellow == Blocks.AIR || currentBellow == Blocks.CAVE_AIR || currentBellow == Blocks.VOID_AIR) {
                world.setBlockState(currentBellowPos, state);
                currentBellowPos = currentBellowPos.subtract(new Vec3i(0, 1, 0));
                currentBellow = world.getBlockState(currentBellowPos).getBlock();
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
