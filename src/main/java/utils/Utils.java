package utils;

import java.util.UUID;

public class Utils {

    public static String generateRandomEmail() {
        return "qa.tixsplit."
                + UUID.randomUUID().toString().substring(0, 8)
                + "@example.com";
    }
}