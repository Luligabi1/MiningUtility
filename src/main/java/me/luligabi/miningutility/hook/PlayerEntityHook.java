package me.luligabi.miningutility.hook;

import me.luligabi.miningutility.registry.ItemRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class PlayerEntityHook {

    public static void tick(PlayerEntity playerEntity) {

        StatusEffectInstance nightVision = new StatusEffectInstance(StatusEffects.NIGHT_VISION, 20*20, 0);

        ItemStack helmet = playerEntity.getEquippedStack(EquipmentSlot.HEAD);

        if(helmet.getItem() == ItemRegistry.MINING_HELMET) {
            if(!playerEntity.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
                playerEntity.addStatusEffect(nightVision);
            }
            if(playerEntity.getActiveStatusEffects().containsValue(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 11*20, 0))) {
                playerEntity.addStatusEffect(nightVision);
                if(playerEntity.getRandom().nextBoolean()) {
                    helmet.damage(1, playerEntity, (entity) -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
                }
            }
        }
    }
}
