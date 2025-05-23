package com.example.demo.generator;

public class PasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    public static String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 characters");
        }

        StringBuilder password = new StringBuilder();
        password.append(randomCharacter(LOWERCASE));
        password.append(randomCharacter(UPPERCASE));
        password.append(randomCharacter(DIGITS));
        password.append(randomCharacter(SPECIAL_CHARACTERS));

        for (int i = 4; i < length; i++) {
            password.append(randomCharacter(LOWERCASE + UPPERCASE + DIGITS + SPECIAL_CHARACTERS));
        }

        return shuffleString(password.toString());
    }

    private static char randomCharacter(String characters) {
        int index = (int) (Math.random() * characters.length());
        return characters.charAt(index);
    }

    private static String shuffleString(String str) {
        char[] array = str.toCharArray();
        java.util.Collections.shuffle(java.util.Arrays.asList(array));
        return new String(array);
    }
}
