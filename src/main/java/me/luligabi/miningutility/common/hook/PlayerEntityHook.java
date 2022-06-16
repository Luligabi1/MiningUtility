package me.luligabi.miningutility.common.hook;

import me.luligabi.miningutility.common.MiningUtility;
import me.luligabi.miningutility.common.item.ItemRegistry;
import me.luligabi.miningutility.mixin.StatusEffectInstanceAccessor;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class PlayerEntityHook {

    public static void tick(PlayerEntity playerEntity, int effectDuration) {

        ItemStack helmet = playerEntity.getEquippedStack(EquipmentSlot.HEAD);

        if(helmet.getItem() == ItemRegistry.MINING_HELMET) {
            if(!playerEntity.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, effectDuration, 0, true, false, false));
            }

            if(playerEntity.getActiveStatusEffects().get(StatusEffects.NIGHT_VISION) instanceof StatusEffectInstanceAccessor instance && instance.getDuration() == effectDuration) {
                ((StatusEffectInstanceAccessor) playerEntity.getActiveStatusEffects().get(StatusEffects.NIGHT_VISION)).setDuration(effectDuration + 2*20);
                if(new MiningUtility().getConfig().getOrDefault("damageMiningHelmetOnUse", true) && Math.random() < 0.25) {
                    helmet.damage(1, playerEntity, (entity) -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
                }
            }
        }
    }

}