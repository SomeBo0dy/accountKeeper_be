package pers.xyj.modules.common.utils;


import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    private static Integer sixBit = 100000;

    public static String getSixBitRandom() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(sixBit, sixBit * 10));
    }
}
