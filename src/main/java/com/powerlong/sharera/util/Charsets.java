package com.powerlong.sharera.util;

import java.io.UnsupportedEncodingException;

public enum Charsets {
    GBK("GBK"),
    UTF8("UTF-8");

    public final String encoding;

    private Charsets(String value) {
        this.encoding = value;
    }

    public String toString(byte[] bytes) {
        return toString(bytes, this.encoding);
    }

    public byte[] getBytes(String s) {
        return getBytes(s, this.encoding);
    }

    public static byte[] getBytes(String s, String encoding) {
        try {
            return s.getBytes(encoding);
        } catch (UnsupportedEncodingException var3) {
            return s.getBytes();
        }
    }

    public static String toString(byte[] bytes, String encoding) {
        try {
            return new String(bytes, encoding);
        } catch (UnsupportedEncodingException var3) {
            return new String(bytes);
        }
    }
}
