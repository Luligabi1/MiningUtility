package me.luligabi.miningutility.common.block;

import me.luligabi.miningutility.common.misc.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class RopeLadderBlock extends LadderBlock {

    public RopeLadderBlock(Direction direction, int blockLimit) {
        super(Properties.ofFullCopy(Blocks.LADDER));
        this.direction = direction;
        this.blockLimit = blockLimit;
    }

    public final Direction direction;
    public final int blockLimit;


    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return true;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity livingEntity, ItemStack itemStack) {
        if(level.isClientSide()) return;
        BlockPos currentPos = pos.relative(direction);
        int ladderLimit = blockLimit;
        while(ladderLimit > 0 && level.getBlockState(currentPos).canBeReplaced()) {
            level.setBlockAndUpdate(currentPos, state);
            currentPos = currentPos.relative(direction);
            ladderLimit--;
            level.playSound(null, currentPos, SoundRegistry.ROPE_LADDER_PLACE.get(), SoundSource.BLOCKS);
        }
    }
}