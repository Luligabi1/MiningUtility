package me.luligabi.miningutility.common.block;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WallMiniTorchBlock extends MiniTorchBlock {

    public WallMiniTorchBlock() {
        super();
        this.setDefaultState((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        Direction[] directions = ctx.getPlacementDirections();

        for (Direction direction : directions) {
            if (direction.getAxis().isHorizontal()) {
                Direction direction2 = direction.getOpposite();
                blockState = blockState.with(FACING, direction2);
                if (blockState.canPlaceAt(worldView, blockPos)) {
                    return blockState;
                }
            }
        }
        return null;
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        return direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : state;
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getBoundingShape(state);
    }

    public static VoxelShape getBoundingShape(BlockState state) {
        return BOUNDING_SHAPES.get(state.get(FACING));
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        Direction direction = state.get(FACING);
        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 0.35D;
        double z = pos.getZ() + 0.5D;
        Direction directionOpposite = direction.getOpposite();
        switch(direction) {
            case NORTH, SOUTH -> {
                world.addParticle(ParticleTypes.SMOKE, x + 0.27D * (double) directionOpposite.getOffsetX(), y + 0.22D, z + 0.37D * (double) directionOpposite.getOffsetZ(), 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, x + 0.27D * (double) directionOpposite.getOffsetX(), y + 0.22D, z + 0.37D * (double) directionOpposite.getOffsetZ(), 0.0D, 0.0D, 0.0D);
            }
            case WEST, EAST -> {
                world.addParticle(ParticleTypes.SMOKE, x + 0.37D * (double) directionOpposite.getOffsetX(), y + 0.22D, z + 0.27D * (double) directionOpposite.getOffsetZ(), 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.FLAME, x + 0.37D * (double) directionOpposite.getOffsetX(), y + 0.22D, z + 0.27D * (double) directionOpposite.getOffsetZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }


    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final Map<Direction, VoxelShape> BOUNDING_SHAPES = ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.createCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.createCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.createCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D));

}