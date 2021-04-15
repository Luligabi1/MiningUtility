package me.luligabi.miningutility.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TunnelExcavatorItem extends Item {

    public TunnelExcavatorItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        if(!world.isClient()) {

            world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
            return super.useOnBlock(context);
        }
        return super.useOnBlock(context);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if(!world.isClient()) {
            if(!playerEntity.abilities.creativeMode) {
                playerEntity.getStackInHand(hand).decrement(1);
            }
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}