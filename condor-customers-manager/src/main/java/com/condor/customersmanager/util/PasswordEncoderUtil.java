package com.condor.customersmanager.util;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class PasswordEncoderUtil {
    private static final Argon2 argon2 = Argon2Factory.create();

    public static String encode(String password) {
        return argon2.hash(2, 65536, 1, password);
    }
}
