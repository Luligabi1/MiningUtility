package me.luligabi.miningutility.neoforge.client;

import me.luligabi.miningutility.client.MiningUtilityClient;
import me.luligabi.miningutility.common.MiningUtility;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = MiningUtility.MOD_ID, dist = Dist.CLIENT)
public class MiningUtilityClientNeoforge {

    public MiningUtilityClientNeoforge() {
        MiningUtilityClient.init();
    }
}