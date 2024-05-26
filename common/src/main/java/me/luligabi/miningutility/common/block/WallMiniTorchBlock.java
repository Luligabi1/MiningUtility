package me.luligabi.miningutility.common.block;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WallMiniTorchBlock extends MiniTorchBlock {

    public WallMiniTorchBlock() {
        super();
        registerDefaultState((stateDefinition.any()).setValue(FACING, Direction.NORTH));
    }


    @Override
    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return WallTorchBlock.canSurvive(levelReader, blockPos, blockState.getValue(FACING));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState blockState = defaultBlockState();
        LevelReader worldView = ctx.getLevel();
        BlockPos blockPos = ctx.getClickedPos();
        Direction[] directions = ctx.getNearestLookingDirections();

        for(Direction direction : directions) {
            if(direction.getAxis().isHorizontal()) {
                Direction direction2 = direction.getOpposite();
                blockState = blockState.setValue(FACING, direction2);
                if(blockState.canSurvive(worldView, blockPos)) {
                    return blockState;
                }
            }
        }
        return null;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState newState, LevelAccessor level, BlockPos pos, BlockPos posFrom) {
        if(direction.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, pos)) {
            return Blocks.AIR.defaultBlockState();
        }
        return state;
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext ctx) {
        return getBoundingShape(state);
    }

    public static VoxelShape getBoundingShape(BlockState state) {
        return BOUNDING_SHAPES.get(state.getValue(FACING));
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        Direction direction = state.getValue(FACING);
        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.35D;
        double z = pos.getZ() + 0.5D;
        Direction directionOpposite = direction.getOpposite();
        switch(direction) {
            case NORTH, SOUTH -> {
                level.addParticle(ParticleTypes.SMOKE, x + 0.27D * (double) directionOpposite.getStepX(), y + 0.22D, z + 0.37D * (double) directionOpposite.getStepZ(), 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.FLAME, x + 0.27D * (double) directionOpposite.getStepX(), y + 0.22D, z + 0.37D * (double) directionOpposite.getStepZ(), 0.0D, 0.0D, 0.0D);
            }
            case WEST, EAST -> {
                level.addParticle(ParticleTypes.SMOKE, x + 0.37D * (double) directionOpposite.getStepX(), y + 0.22D, z + 0.27D * (double) directionOpposite.getStepZ(), 0.0D, 0.0D, 0.0D);
                level.addParticle(ParticleTypes.FLAME, x + 0.37D * (double) directionOpposite.getStepX(), y + 0.22D, z + 0.27D * (double) directionOpposite.getStepZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }




    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final Map<Direction, VoxelShape> BOUNDING_SHAPES = ImmutableMap.of(
        Direction.NORTH, Block.box(5.5D, 3D, 11D, 10.5D, 13D, 16D),
        Direction.SOUTH, Block.box(5.5D, 3D, 0D, 10.5D, 13D, 5D),
        Direction.WEST, Block.box(11D, 3D, 5.5D, 16D, 13D, 10.5D),
        Direction.EAST, Block.box(0D, 3D, 5.5D, 5D, 13D, 10.5D)
    );

}