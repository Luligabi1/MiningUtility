package me.luligabi.miningutility.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class InvertedRopeLadderBlock extends LadderBlock {

    public InvertedRopeLadderBlock(Settings settings) {
        super(Settings.copy(Blocks.LADDER));
    }

    //TODO: Add check to avoid mindless placing of ladders, causing gigantic lag.
    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(!world.isClient()) {
            BlockPos currentAbovePos = pos.up();
            Block currentAbove = world.getBlockState(currentAbovePos).getBlock();
            while(currentAbove == Blocks.AIR || currentAbove == Blocks.CAVE_AIR || currentAbove == Blocks.VOID_AIR) {
                world.setBlockState(currentAbovePos, state);
                currentAbovePos = currentAbovePos.add(new Vec3i(0, 1, 0));
                currentAbove = world.getBlockState(currentAbovePos).getBlock();
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }
}
