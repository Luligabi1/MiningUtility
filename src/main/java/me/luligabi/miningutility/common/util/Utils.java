package me.luligabi.miningutility.common.util;

public class Utils {
    public static double roundNumber(double value, int precision) {
        var scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}