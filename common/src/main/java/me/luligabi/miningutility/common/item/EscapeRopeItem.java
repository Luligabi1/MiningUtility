package me.luligabi.miningutility.common.item;

import me.luligabi.miningutility.common.MiningUtility;
import me.luligabi.miningutility.common.misc.SoundRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

public class EscapeRopeItem extends Item {

    public EscapeRopeItem() {
        super(new Item.Properties().stacksTo(16).arch$tab(MiningUtility.ITEM_GROUP));
    }

    protected EscapeRopeItem(Properties properties) {
        super(properties.arch$tab(MiningUtility.ITEM_GROUP));
    }

    protected void use(Level level, Player player, ItemStack stack) {
        BlockPos pos = player.getOnPos().above();
        while(!level.canSeeSky(pos) && pos.getY() < level.dimensionType().logicalHeight() - 2) {
            pos = pos.above();
        }
        if(pos.getY() != player.getOnPos().above().getY() && level.getBlockState(pos.above()).isAir() && level.getBlockState(pos.above(2)).isAir()) {
            player.teleportTo(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
            stack.consume(1, player);
            player.playNotifySound(SoundRegistry.ESCAPE_ROPE_THROW.get(), SoundSource.PLAYERS, 1F, 1F);
        } else {
            level.addParticle(ParticleTypes.SMOKE, player.getX(), player.getY(), player.getZ(), 0, 0, 0);
        }
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int i) {
        if(!(entity instanceof Player) || 72000 - i < 20) return;
        use(level, (Player) entity, stack);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if(level.isClientSide) return InteractionResultHolder.pass(stack);

        player.startUsingItem(hand);
        return InteractionResultHolder.success(stack);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity livingEntity) { // Same as a bow
        return 72000;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> text, TooltipFlag tooltipFlag) {
        text.add(Component.translatable("item.miningutility.escape_rope.tooltip"));
    }
}