package br.com.stark_bank.application.util;

import java.util.Random;

public final class NameGenerator {

    private static final String[] FIRST_NAMES = {
            "Ana", "João", "Maria", "Pedro", "Lucas", "Carla", "Anthony", "Peter"
    };

    private static final String[] LAST_NAMES = {
            "Silva", "Santos", "Oliveira", "Pereira", "Costa", "Stark", "Parker"
    };

    private static final Random RANDOM = new Random();

    public static String randomName() {
        return FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)] + " " +
                LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
    }

}
