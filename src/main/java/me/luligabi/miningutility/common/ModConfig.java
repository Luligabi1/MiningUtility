package me.luligabi.miningutility.common;

import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    @Entry(isSlider = true, min = 2, max = 320) public static int ropeLadderBlockLimit = 64;
    @Entry(isSlider = true, min = 2, max = 320) public static int invertedRopeLadderBlockLimit = 64;
    @Entry public static boolean damageMiningHelmetOnUse = true;
}