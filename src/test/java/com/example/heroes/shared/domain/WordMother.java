package com.example.heroes.shared.domain;


import java.util.Random;

public final class WordMother {
    private static final int MIN_STRING_SIZE = 0;
    private static final int MAX_STRING_SIZE = 255;

    public static String random() {
        return randomMinMax(MIN_STRING_SIZE, MAX_STRING_SIZE);
    }

    public static String randomMin(int min) {
        return randomMinMax(min, MAX_STRING_SIZE);
    }

    public static String randomMax(int max) {
        return randomMinMax(MIN_STRING_SIZE, max);
    }

    public static String randomMinMax(int min, int max) {
        Random random = new Random();
        int maxSize = random.nextInt(max - min) + min;
        return random.ints(Character.MIN_VALUE, Character.MAX_VALUE + 1)
                .filter(codePoint -> Character.isDefined(codePoint)
                        && !Character.isSurrogate((char) codePoint)
                        && Character.getType(codePoint) != Character.PRIVATE_USE)
                .limit(maxSize)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
