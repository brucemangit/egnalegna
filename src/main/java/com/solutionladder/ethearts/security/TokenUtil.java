package com.solutionladder.ethearts.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;
 
public class TokenUtil {
 
    //Get this value from database for each account?
    public static final String SALT = "SodiumChloride";
 
    public static String createToken(UserDetails userDetails) {
        long expires = System.currentTimeMillis() + 1000L * 60 * 60;
        return userDetails.getUsername() + ":" + expires + ":" + computeSignature(userDetails, expires);
    }
 
    public static String computeSignature(UserDetails userDetails, long expires) {
        StringBuilder signatureBuilder = new StringBuilder();
        signatureBuilder.append(userDetails.getUsername()).append(":");
        signatureBuilder.append(expires).append(":");
        signatureBuilder.append(userDetails.getPassword()).append(":");
        signatureBuilder.append(TokenUtil.SALT);
 
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
    }

    /**
     * Better algorithm for disecting the token shall be here
     * @param authToken
     * @return
     */
    public static String getUserNameFromToken(String authToken) {
        if (authToken == null) {
            return null;
        }
        String[] parts = authToken.split(":");
        return parts[0];
    }
 
    /**
     * Fix the token to be inclusive and contain all the group/username..
     * And make it stronger.
     * 
     * @param authToken
     * @param userDetails
     * @return
     */
    public static boolean validateToken(String authToken, UserDetails userDetails) {
        String[] parts = authToken.split(":");
        if (parts.length < 3) {
            return false;
        }
        long expires = Long.parseLong(parts[1]);
        String signature = parts[2];
        String signatureToMatch = computeSignature(userDetails, expires);
        return expires >= System.currentTimeMillis() && signature.equals(signatureToMatch);
    }
}