package com.capgemini.helpers;

import com.capgemini.constants.BankingConstants;

import java.util.Random;

public class GenerateRandomValues {

    private static Random random = new Random();

    public static long generateRandomAccountNumber() {
        return random.nextLong();
    }

    public static String randomNameGenerator() {
        char[] text = new char[BankingConstants.RANDOM_STRING_LENGTH];
        for (int i = 0; i < BankingConstants.RANDOM_STRING_LENGTH; i++) {
            text[i] = BankingConstants.RANDOM_CHARS.charAt(random.nextInt(BankingConstants.RANDOM_CHARS.length()));
        }
        return new String(text);
    }
}
