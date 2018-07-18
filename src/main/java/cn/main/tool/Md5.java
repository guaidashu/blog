package cn.main.tool;

import java.util.Base64;
import java.util.Base64.Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

    public static String get(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            Encoder base64 = Base64.getEncoder();
            try {
                str = base64.encodeToString(md5.digest(str.getBytes("utf-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return str;
    }

}
