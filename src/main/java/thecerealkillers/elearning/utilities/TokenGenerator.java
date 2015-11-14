package thecerealkillers.elearning.utilities;

import java.util.UUID;

/**
 * Created by cuvidk on 11/10/2015.
 */

public class TokenGenerator {
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}