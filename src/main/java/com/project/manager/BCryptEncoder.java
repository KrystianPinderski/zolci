package com.project.manager;


import org.mindrot.jbcrypt.BCrypt;

public class BCryptEncoder {
    private static final BCrypt bcrypt = new BCrypt();

    public static String encode(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean check(String setPassword, String correctPassword) {
        return bcrypt.checkpw(setPassword, correctPassword);
    }
}
