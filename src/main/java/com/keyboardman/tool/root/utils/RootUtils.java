package com.keyboardman.tool.root.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RootUtils {

    /**
     * MD5j加密
     * @param password
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String MD5(String password) throws UnsupportedEncodingException {
        MessageDigest md5 = null;
        String passwordResult = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            passwordResult= base64Encoder.encode(md5.digest(password.getBytes("utf-8")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return passwordResult;
    }
}
