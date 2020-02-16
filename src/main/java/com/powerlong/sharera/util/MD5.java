package com.powerlong.sharera.util;

import java.security.MessageDigest;

public class MD5 {
    static final char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public MD5() {
    }

    public static String crypt(String str) {
        return crypt(str, Charsets.GBK.encoding);
    }

    public static String crypt(String str, String charSet) {
        if (str != null && str.length() != 0 && charSet != null) {
            return getMD5(Charsets.getBytes(str, charSet));
        } else {
            throw new IllegalArgumentException("String or charset to encript cannot be null or zero length");
        }
    }

    /** @deprecated */
    @Deprecated
    public static String getMD5(byte[] source) {
        return crypt(source);
    }

    public static String crypt(byte[] source) {
        String s = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte[] tmp = md.digest();
            char[] str = new char[32];
            int k = 0;

            for(int i = 0; i < 16; ++i) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            s = new String(str);
            return s;
        } catch (Exception var8) {
            throw new IllegalArgumentException(var8);
        }
    }
}

