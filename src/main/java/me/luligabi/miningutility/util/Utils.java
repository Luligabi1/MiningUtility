package me.luligabi.miningutility.util;

public class Utils {

    public static double roundNumber(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
