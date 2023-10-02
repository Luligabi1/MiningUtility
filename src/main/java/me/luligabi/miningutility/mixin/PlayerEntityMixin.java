package me.luligabi.miningutility.mixin;

import me.luligabi.miningutility.common.ModConfig;
import me.luligabi.miningutility.common.registry.ItemRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "tick", at = @At("RETURN"))
    public void injectTick(CallbackInfo info) {
        var playerEntity = ((PlayerEntity) (Object) this);
        var helmet = playerEntity.getEquippedStack(EquipmentSlot.HEAD);
        if (helmet.getItem() == ItemRegistry.MINING_HELMET) {
            if (!playerEntity.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
                playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 11 * 20, 0, true, false, false));
            }
            if (playerEntity.getActiveStatusEffects().get(StatusEffects.NIGHT_VISION) instanceof StatusEffectInstanceAccessor instance && instance.getDuration() == 11 * 20) {
                ((StatusEffectInstanceAccessor) playerEntity.getActiveStatusEffects().get(StatusEffects.NIGHT_VISION)).setDuration(11 * 20 + 2 * 20);
                if (ModConfig.damageMiningHelmetOnUse && Math.random() < 0.25) {
                    helmet.damage(1, playerEntity, (entity) -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
                }
            }
        }
    }
}