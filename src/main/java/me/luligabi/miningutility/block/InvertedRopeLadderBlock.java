package me.luligabi.miningutility.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LadderBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class InvertedRopeLadderBlock extends LadderBlock {

    public InvertedRopeLadderBlock(Settings settings) {
        super(Settings.copy(Blocks.LADDER));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(!world.isClient()) {
            BlockPos currentAbovePos = pos.up();
            Block currentAbove = world.getBlockState(currentAbovePos).getBlock();
            int ladderLimit = 48; //TODO: Add config to change value
            Block[] airBlockList = {Blocks.AIR, Blocks.CAVE_AIR, Blocks.VOID_AIR};
            while(Arrays.asList(airBlockList).contains(currentAbove) && ladderLimit > 0) {
                world.setBlockState(currentAbovePos, state);
                currentAbovePos = currentAbovePos.add(new Vec3i(0, 1, 0));
                currentAbove = world.getBlockState(currentAbovePos).getBlock();
                ladderLimit--;
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("block.miningutility.inverted_rope_ladder.tooltip").formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("block.miningutility.rope_ladders.common.tooltip").formatted(Formatting.GRAY));
    }
}
