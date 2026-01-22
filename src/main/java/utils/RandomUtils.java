package utils;

import java.security.SecureRandom;

/**
 * RandomUtils
 *
 * Utility class for generating random strings, numbers,
 * and alphanumeric values for test data.
 */
public class RandomUtils {

    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String ALPHANUMERIC = ALPHABETS + NUMBERS;

    private static final SecureRandom random = new SecureRandom();

    /**
     * Generates a random alphabetic string.
     *
     * @param length Length of the string
     * @return Random alphabetic text
     */
    public static String randomText(int length) {
        return generateRandom(ALPHABETS, length);
    }

    /**
     * Generates a random numeric string.
     *
     * @param length Length of the number
     * @return Random numeric value as String
     */
    public static String randomNumber(int length) {
        return generateRandom(NUMBERS, length);
    }

    /**
     * Generates a random alphanumeric string.
     *
     * @param length Length of the string
     * @return Random alphanumeric value
     */
    public static String randomAlphaNumeric(int length) {
        return generateRandom(ALPHANUMERIC, length);
    }

    /**
     * Generates a random email address.
     *
     * @return Random email
     */
    public static String randomEmail() {
        return "user_" + randomAlphaNumeric(6) + "@testmail.com";
    }

    /**
     * Generates a random username.
     *
     * @return Random username
     */
    public static String randomUsername() {
        return "user_" + randomText(5);
    }

    /**
     * Generates a random phone number (10 digits).
     *
     * @return Random phone number
     */
    public static String randomPhoneNumber() {
        return randomNumber(10);
    }

    // ===================== PRIVATE HELPER =====================

    /**
     * Core random generator method.
     *
     * @param source Source characters
     * @param length Required length
     * @return Random string
     */
    private static String generateRandom(String source, int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(source.charAt(random.nextInt(source.length())));
        }
        return sb.toString();
    }
}
