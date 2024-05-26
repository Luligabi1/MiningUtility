package me.luligabi.miningutility.common.item;

import me.luligabi.miningutility.common.MiningUtility;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class EscapeRopeItem extends Item {

    public EscapeRopeItem() {
        super(new Item.Properties().stacksTo(16).arch$tab(MiningUtility.ITEM_GROUP));
    }

    protected EscapeRopeItem(Properties properties) {
        super(properties.arch$tab(MiningUtility.ITEM_GROUP));
    }

    protected void use(Level level, Player player, ItemStack stack) {
        BlockPos pos = player.getOnPos();
        while(!level.canSeeSky(pos) && pos.getY() < level.dimensionType().logicalHeight() - 2) {
            pos = pos.above();
        }

        if(pos.getY() != player.getOnPos().getY() && level.getBlockState(pos.above()).isAir() && level.getBlockState(pos.above(2)).isAir()) {
            // FIXME damage?
            player.teleportToWithTicket(
                pos.getX() + 0.5,
                pos.getY() + 1,
                pos.getZ() + 0.5
            );
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
    public int getUseDuration(ItemStack itemStack) { // Same as a bow
        return 72000;
    }

}