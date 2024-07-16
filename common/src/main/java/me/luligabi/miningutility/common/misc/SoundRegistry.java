package me.luligabi.miningutility.common.misc;

import dev.architectury.registry.registries.RegistrySupplier;
import me.luligabi.miningutility.common.MiningUtility;
import net.minecraft.sounds.SoundEvent;

public class SoundRegistry {

    public static final RegistrySupplier<SoundEvent> ESCAPE_ROPE_THROW = MiningUtility.SOUND_EVENTS.register(
        MiningUtility.id("escape_rope_throw"),
        () -> SoundEvent.createVariableRangeEvent(MiningUtility.id("escape_rope_throw"))
    );

    public static final RegistrySupplier<SoundEvent> ENDER_ESCAPE_ROPE_TELEPORT = MiningUtility.SOUND_EVENTS.register(
        MiningUtility.id("ender_escape_rope_teleport"),
        () -> SoundEvent.createVariableRangeEvent(MiningUtility.id("ender_escape_rope_teleport"))
    );

    public static final RegistrySupplier<SoundEvent> MINING_HELMET_EQUIP = MiningUtility.SOUND_EVENTS.register(
        MiningUtility.id("mining_helmet_equip"),
        () -> SoundEvent.createVariableRangeEvent(MiningUtility.id("mining_helmet_equip"))
    );

    public static final RegistrySupplier<SoundEvent> ROPE_LADDER_PLACE = MiningUtility.SOUND_EVENTS.register(
        MiningUtility.id("rope_ladder_place"),
        () -> SoundEvent.createVariableRangeEvent(MiningUtility.id("rope_ladder_place"))
    );

    public static void init() {
        // NO-OP
    }

    private SoundRegistry() {
        // NO-OP
    }
}