package me.luligabi.miningutility.neoforge.common;

import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import net.neoforged.fml.common.Mod;

import me.luligabi.miningutility.common.MiningUtility;

@Mod(MiningUtility.MOD_ID)
public final class MiningUtilityNeoForge {

    public MiningUtilityNeoForge() {
        MiningUtility.init();
    }
}