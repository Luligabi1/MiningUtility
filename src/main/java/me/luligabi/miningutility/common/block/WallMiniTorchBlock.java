package me.luligabi.miningutility.common.block;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WallMiniTorchBlock extends MiniTorchBlock {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final Map<Direction, VoxelShape> BOUNDING_SHAPES = ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.createCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.createCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.createCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D));

    public WallMiniTorchBlock() {
        super();
        this.setDefaultState((this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        var direction = state.get(FACING);
        var blockPos = pos.offset(direction.getOpposite());
        var blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction);
    }

    @Override @Nullable
    public BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
        var blockState = this.getDefaultState();
        WorldView worldView = ctx.getWorld();
        var blockPos = ctx.getBlockPos();
        var directions = ctx.getPlacementDirections();
        for (var direction : directions) {
            if (direction.getAxis().isHorizontal()) {
                var direction2 = direction.getOpposite();
                blockState = blockState.with(FACING, direction2);
                if (blockState.canPlaceAt(worldView, blockPos)) {
                    return blockState;
                }
            }
        }
        return null;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        return direction.getOpposite() == state.get(FACING) && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : state;
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getBoundingShape(state);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        var direction = state.get(FACING);
        var x = pos.getX() + 0.5D;
        var y = pos.getY() + 0.35D;
        var z = pos.getZ() + 0.5D;
        var directionOpposite = direction.getOpposite();
        switch (direction) {
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

    public static VoxelShape getBoundingShape(BlockState state) {
        return BOUNDING_SHAPES.get(state.get(FACING));
    }
}