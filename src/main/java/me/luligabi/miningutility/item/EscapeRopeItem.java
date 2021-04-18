package me.luligabi.miningutility.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
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

        if(!player.isSneaking()) {
            CompoundTag itemCompoundTag = item.getTag();

            if(itemCompoundTag.getDouble("x") != 0 && itemCompoundTag.getDouble("y") != 0 && itemCompoundTag.getDouble("z") != 0) { //if coordinates are all zeros, the position wasn't set.
                player.teleport(itemCompoundTag.getDouble("x"),
                        itemCompoundTag.getDouble("y"),
                        itemCompoundTag.getDouble("z"));
                item.damage(1, player, (entity) -> player.sendToolBreakStatus(player.getActiveHand()));
            } else {
                player.sendMessage(new TranslatableText(
                        "message.miningutility.escape_rope.fail").setStyle(Style.EMPTY.withColor(Formatting.RED)), true);
            }
        } else {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putDouble("x", player.getX());
            compoundTag.putDouble("y", player.getY());
            compoundTag.putDouble("z", player.getZ());

            item.setTag(compoundTag);
            player.sendMessage(new TranslatableText(
                    "message.miningutility.escape_rope.setteleport", player.getX(), player.getY(), player.getZ())
                    .setStyle(Style.EMPTY.withColor(Formatting.YELLOW)), true);
        }
        return TypedActionResult.success(item);
    }

}