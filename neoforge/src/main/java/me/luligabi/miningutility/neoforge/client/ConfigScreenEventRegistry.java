package me.luligabi.miningutility.neoforge.client;

import me.luligabi.miningutility.client.ConfigScreen;
import me.luligabi.miningutility.common.MiningUtility;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@EventBusSubscriber(modid = MiningUtility.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ConfigScreenEventRegistry {

    @SubscribeEvent
    public static void onPostInit(FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(
            IConfigScreenFactory.class,
            () -> (client, parent) -> ConfigScreen.createConfigScreen(parent)
        );
    }
}