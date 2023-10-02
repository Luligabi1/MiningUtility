package me.luligabi.miningutility.common.block;

import me.luligabi.miningutility.common.ModConfig;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class RopeLadderBlock extends LadderBlock {
    public RopeLadderBlock() {
        super(FabricBlockSettings.copy(Blocks.LADDER));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (!world.isClient()) {
            var currentBellowPos = pos.down();
            var currentBellow = world.getBlockState(currentBellowPos).getBlock();
            var ladderLimit = ModConfig.ropeLadderBlockLimit;
            var airBlockList = new Block[]{Blocks.AIR, Blocks.CAVE_AIR, Blocks.VOID_AIR};
            while (Arrays.asList(airBlockList).contains(currentBellow) && ladderLimit > 0) {
                world.setBlockState(currentBellowPos, state);
                currentBellowPos = currentBellowPos.subtract(new Vec3i(0, 1, 0));
                currentBellow = world.getBlockState(currentBellowPos).getBlock();
                ladderLimit--;
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.miningutility.rope_ladder.tooltip").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("block.miningutility.rope_ladders.common.tooltip").formatted(Formatting.GRAY));
    }
}