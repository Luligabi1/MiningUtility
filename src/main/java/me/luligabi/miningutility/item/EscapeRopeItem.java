package me.luligabi.miningutility.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
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

            try {
                if(player.getEntityWorld().toString().equals(itemCompoundTag.getString("world"))) {
                    player.teleport(itemCompoundTag.getDouble("x"),
                            itemCompoundTag.getDouble("y"),
                            itemCompoundTag.getDouble("z"));
                    item.damage(1, player, (entity) -> player.sendToolBreakStatus(player.getActiveHand()));
                } else {
                    System.out.println("different dimensions");
                }
            } catch(NullPointerException exception) {
                System.out.println("can't teleport");
            }

        } else {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putDouble("x", player.getX());
            compoundTag.putDouble("y", player.getY());
            compoundTag.putDouble("z", player.getZ());
            compoundTag.putString("world", player.getEntityWorld().toString());

            item.setTag(compoundTag);
            System.out.println("setting tags");
        }
        return TypedActionResult.success(item);
    }

}