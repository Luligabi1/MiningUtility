package me.luligabi.miningutility.common.misc;

import dev.architectury.registry.registries.RegistrySupplier;
import me.luligabi.miningutility.common.MiningUtility;
import net.minecraft.sounds.SoundEvent;

public class SoundRegistry {

    public static final RegistrySupplier<SoundEvent> ENDER_ESCAPE_ROPE_TELEPORT = MiningUtility.SOUND_EVENTS.register(
        MiningUtility.id("ender_escape_rope_teleport"),
        () -> SoundEvent.createVariableRangeEvent(MiningUtility.id("ender_escape_rope_teleport"))
    );

    public static void init() {
        // NO-OP
    }

    private SoundRegistry() {
        // NO-OP
    }
}