package me.luligabi.miningutility.common.item;

import me.luligabi.miningutility.common.MiningUtility;
import me.luligabi.miningutility.common.item.misc.ArmorMaterialRegistry;
import me.luligabi.miningutility.mixin.MobEffectInstanceAccessor;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MiningHelmetItem extends ArmorItem {

    public MiningHelmetItem() {
        super(ArmorMaterialRegistry.MINING_HELMET, ArmorItem.Type.HELMET, new Item.Properties().arch$tab(MiningUtility.ITEM_GROUP));
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        if(entity instanceof Player player) {
            if(player.getItemBySlot(EquipmentSlot.HEAD) != itemStack) return;

            int effectDuration = 12*20;
            if(player.getActiveEffectsMap().get(MobEffects.NIGHT_VISION) instanceof MobEffectInstanceAccessor instance) {
                if(instance.getDuration() > effectDuration - 2 * 20) return;
                instance.setDuration(effectDuration + 2 * 20);
                if(/*FIXME MiningUtility.CONFIG.damageMiningHelmetOnUse &&*/ Math.random() < 0.25) {
                    itemStack.hurtAndBreak(1, player, EquipmentSlot.HEAD);
                }
            } else {
                player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, effectDuration, 0, true, false, false));
            }
        }
    }

}