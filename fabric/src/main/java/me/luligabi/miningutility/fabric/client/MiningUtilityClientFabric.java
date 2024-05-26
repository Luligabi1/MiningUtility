package me.luligabi.miningutility.fabric.client;

import me.luligabi.miningutility.client.MiningUtilityClient;
import net.fabricmc.api.ClientModInitializer;

public final class MiningUtilityClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MiningUtilityClient.init();
    }
}