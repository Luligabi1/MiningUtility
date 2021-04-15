package me.luligabi.miningutility.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;

import net.minecraft.world.World;


public class EscapeRopeItem extends Item {

    public EscapeRopeItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack item = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        escapeTeleport();
        item.damage(1, player, (entity) -> player.sendToolBreakStatus(player.getActiveHand()));
        return TypedActionResult.success(item);
    }

    private void escapeTeleport() {
        //TODO
    }
}