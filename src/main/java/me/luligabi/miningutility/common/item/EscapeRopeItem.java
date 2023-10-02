package me.luligabi.miningutility.common.item;

import me.luligabi.miningutility.common.util.Utils;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
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
        var stack = player.getStackInHand(hand);
        if (!world.isClient()) {
            player.setCurrentHand(hand);
            if (!player.isSneaking()) {
                var nbt = stack.getNbt();
                if (nbt != null && nbt.contains("position")) {
                    var tag = nbt.getCompound("position");
                    if (tag.getString("dimension").equals(world.getRegistryKey().toString())) {
                        player.teleport(tag.getDouble("x"), tag.getDouble("y"), tag.getDouble("z"));
                        stack.damage(1, player, (entity) -> player.sendToolBreakStatus(player.getActiveHand()));
                    } else {
                        player.sendMessage(Text.translatable("message.miningutility.escape_rope.fail.other_dimension").setStyle(Style.EMPTY.withColor(Formatting.RED)), true);
                    }
                } else {
                    player.sendMessage(Text.translatable("message.miningutility.escape_rope.fail").setStyle(Style.EMPTY.withColor(Formatting.RED)), true);
                }
            } else {
                var compound = stack.getOrCreateNbt();
                var tag = new NbtCompound();
                tag.putDouble("x", player.getX());
                tag.putDouble("y", player.getY());
                tag.putDouble("z", player.getZ());
                tag.putString("dimension", world.getRegistryKey().toString());
                compound.put("position", tag);
                stack.setNbt(compound);
                player.sendMessage(Text.translatable("message.miningutility.escape_rope.setteleport", Utils.roundNumber(player.getX(), 1), Utils.roundNumber(player.getY(), 1), Utils.roundNumber(player.getZ(), 1)).setStyle(Style.EMPTY.withColor(Formatting.YELLOW)), true);
            }
            return TypedActionResult.success(stack);
        }
        return TypedActionResult.fail(stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.getNbt().getSize() >= 2) {
            tooltip.add(Text.translatable("item.miningutility.escape_rope.tooltip.1.set").formatted(Formatting.GRAY).append(Text.translatable("item.miningutility.escape_rope.tooltip.1.coordinates", Utils.roundNumber(stack.getNbt().getDouble("x"), 1), Utils.roundNumber(stack.getNbt().getDouble("y"), 1), Utils.roundNumber(stack.getNbt().getDouble("z"), 1)).formatted(Formatting.YELLOW)));
        } else {
            tooltip.add(Text.translatable("item.miningutility.escape_rope.tooltip.1.unset").formatted(Formatting.GRAY));
        }
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("item.miningutility.escape_rope.tooltip.2").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.miningutility.escape_rope.tooltip.3").formatted(Formatting.GRAY));
    }
}