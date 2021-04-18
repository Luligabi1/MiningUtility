package me.luligabi.miningutility.item;

import me.luligabi.miningutility.util.Utils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


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
                    "message.miningutility.escape_rope.setteleport", Utils.roundNumber(player.getX(), 1), Utils.roundNumber(player.getY(), 1), Utils.roundNumber(player.getZ(), 1))
                    .setStyle(Style.EMPTY.withColor(Formatting.YELLOW)), true);
        }
        return TypedActionResult.success(item);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(stack.getTag().getSize() >= 2) {
            tooltip.add(new TranslatableText("item.miningutility.escape_rope.tooltip.1.set").formatted(Formatting.GRAY).append(new TranslatableText("item.miningutility.escape_rope.tooltip.1.coordinates",
                    Utils.roundNumber(stack.getTag().getDouble("x"), 1), Utils.roundNumber(stack.getTag().getDouble("y"), 1), Utils.roundNumber(stack.getTag().getDouble("z"), 1)).formatted(Formatting.YELLOW)));
        } else {
            tooltip.add(new TranslatableText("item.miningutility.escape_rope.tooltip.1.unset").formatted(Formatting.GRAY));
        }
        tooltip.add(new LiteralText(""));

        tooltip.add(new TranslatableText("item.miningutility.escape_rope.tooltip.2").formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText("item.miningutility.escape_rope.tooltip.3").formatted(Formatting.GRAY));
    }
}