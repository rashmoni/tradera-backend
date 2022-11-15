package com.novare.traderabackend.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordEncryptor {

    public static String getSalt() throws NoSuchAlgorithmException {

        // Create random salt using the “SHA1PRNG” pseudo-random number generator algorithm.
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        // Create an array for salt.
        byte[] salt = new byte[16];
        // Get a random salt.
        sr.nextBytes(salt);

        return salt.toString();
    }

    public static String get_SHA_256_securePassword(String passwordToHash,
                                                    String salt) throws NoSuchAlgorithmException {

        // Create MessageDigest instance for SHA-256 algorithm.
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        // Add password bytes to digest.
        md.update(salt.getBytes());
        // Get the hash's bytes.
        byte[] bytes = md.digest(passwordToHash.getBytes());

        // Convert bytes[] from decimal format
        // to hexadecimal format.
        StringBuilder sb = new StringBuilder();

        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16)
                    .substring(1));
        }

        String SHA_256_SecurePassword = sb.toString();

        return SHA_256_SecurePassword;
    }
}
