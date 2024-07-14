package me.luligabi.miningutility.common.item;

import me.luligabi.miningutility.common.item.misc.DataComponentRegistry;
import me.luligabi.miningutility.common.misc.SoundRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.List;

@SuppressWarnings("DataFlowIssue")
public class EnderEscapeRopeItem extends EscapeRopeItem {

    public EnderEscapeRopeItem() {
        super(new Item.Properties()
            .stacksTo(1)
            .component(DataComponentRegistry.BLOCK_POS.get(), BlockPos.ZERO)
            .component(DataComponentRegistry.DIMENSION.get(), null) // TODO check if this causes any issues
        );
    }

    @Override
    protected void use(Level level, Player player, ItemStack stack) {
        if(level.isClientSide()) return;
        if(!player.isShiftKeyDown()) {
            if(isPositionSet(stack)) {
                if(isSetToSameDimension(stack, level)) {
                    BlockPos pos = stack.get(DataComponentRegistry.BLOCK_POS.get());
                    player.teleportTo(pos.getX(), pos.getY(), pos.getZ());
                    stack.consume(1, player);
                    level.playSound(
                        null,
                        player.getX(), player.getY(), player.getZ(),
                        SoundRegistry.ENDER_ESCAPE_ROPE_TELEPORT.get(),
                        SoundSource.PLAYERS
                    );
                    level.broadcastEntityEvent(player, EntityEvent.TELEPORT);
                } else {
                    player.displayClientMessage(
                        Component.translatable(
                            "message.miningutility.ender_escape_rope.fail.other_dimension"
                        ).withStyle(ChatFormatting.RED),
                        true
                    );
                }
            } else {
                player.displayClientMessage(
                    Component.translatable(
                        "message.miningutility.ender_escape_rope.fail"
                    ).withStyle(ChatFormatting.RED),
                    true
                );
            }
        } else {
            BlockPos newPos = player.getOnPos().above();
            DataComponentPatch patch = DataComponentPatch.builder()
                .set(DataComponentRegistry.BLOCK_POS.get(), newPos)
                .set(DataComponentRegistry.DIMENSION.get(), level.dimensionTypeRegistration())
                .build();
            stack.applyComponents(patch);

            player.displayClientMessage(
                Component.translatable(
                    "message.miningutility.ender_escape_rope.setteleport",
                    newPos.getX(),
                    newPos.getY(),
                    newPos.getZ()
                ).withStyle(ChatFormatting.YELLOW),
                true
            );
            // TODO sound?
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag tooltipFlag) {
        if(isPositionSet(stack)) {
            BlockPos pos = stack.get(DataComponentRegistry.BLOCK_POS.get());
            tooltip.add(
                Component.translatable(
                    "item.miningutility.ender_escape_rope.tooltip.1.set"
                ).withStyle(ChatFormatting.GRAY)
                .append(
                    Component.translatable(
                        "item.miningutility.ender_escape_rope.tooltip.1.coordinates",
                        pos.getX(),
                        pos.getY(),
                        pos.getZ()
                    ).withStyle(ChatFormatting.YELLOW)
                )
            );
        } else {
            tooltip.add(Component.translatable("item.miningutility.ender_escape_rope.tooltip.1.unset").withStyle(ChatFormatting.GRAY));
        }

        tooltip.add(Component.empty());
        tooltip.add(Component.translatable("item.miningutility.ender_escape_rope.tooltip.2").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("item.miningutility.ender_escape_rope.tooltip.3").withStyle(ChatFormatting.GRAY));
    }


    private boolean isPositionSet(ItemStack stack) {
        BlockPos pos = stack.get(DataComponentRegistry.BLOCK_POS.get());
        return pos.getX() != 0 && pos.getY() != 0 && pos.getZ() != 0;
    }

    private boolean isSetToSameDimension(ItemStack stack, Level level) {
        DimensionType stackDimension = stack.get(DataComponentRegistry.DIMENSION.get()).value();
        return stackDimension.equals(level.dimensionType());
    }
}