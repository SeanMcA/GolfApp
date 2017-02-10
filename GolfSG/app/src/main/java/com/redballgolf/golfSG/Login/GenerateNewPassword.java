package com.redballgolf.golfSG.Login;

import java.security.SecureRandom;
import java.util.Random;


public class GenerateNewPassword {
    public static String create() {
        // Pick from some letters that won't be easily mistaken for each
        // other. So, for example, omit o O and 0, 1 l and L.
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
        Random RANDOM = new SecureRandom();
        String password = "";
        for (int i = 0; i < 8; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            password += letters.substring(index, index + 1);
        }
        return password;
    }
}
