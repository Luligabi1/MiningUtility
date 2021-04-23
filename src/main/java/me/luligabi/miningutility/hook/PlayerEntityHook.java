package me.luligabi.miningutility.hook;

import me.luligabi.miningutility.registry.ItemRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class PlayerEntityHook {

    public static void tick(PlayerEntity playerEntity, CallbackInfo info) {

        StatusEffectInstance nightVision = new StatusEffectInstance(StatusEffects.NIGHT_VISION, 13*20, 0);

        if(playerEntity.getEquippedStack(EquipmentSlot.HEAD).getItem() == ItemRegistry.MINING_HELMET) {
            if(!playerEntity.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
                playerEntity.addStatusEffect(nightVision);
            }
            if(playerEntity.getActiveStatusEffects().containsValue(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 11*20, 0))) {
                playerEntity.addStatusEffect(nightVision);
            }
        }
        info.cancel();
    }
}
