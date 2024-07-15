package me.luligabi.miningutility.client;

import me.luligabi.miningutility.common.ModConfig;
import net.minecraft.client.gui.screens.Screen;

public class ConfigScreen {

    public static Screen createConfigScreen(Screen parent) {
        return ModConfig.HANDLER.generateGui().generateScreen(parent);
    }
}