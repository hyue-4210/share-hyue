package com.powerlong.sharera.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5Util {
	private static final String hexDigIts[] =
		{"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"}; /**
     * MD5加密
     * @param origin 字符
     * @param charsetname 编码
     * @return
     */
    public static String MD5Encode(String origin){
    	String resultString = null;
    	try{
    		resultString = new String(origin);
    		MessageDigest md = MessageDigest.getInstance("MD5");
    		resultString = byteArrayToHexString(md.digest(resultString.getBytes("utf8")));
    	}catch (Exception e){
			e.printStackTrace();
    	}
    	return resultString;
    }

    private static String byteArrayToHexString(byte b[]){
    	StringBuffer resultSb = new StringBuffer();
    	for(int i = 0; i < b.length; i++){
    		resultSb.append(byteToHexString(b[i]));
    	}
    	return resultSb.toString();
    }
    private static String byteToHexString(byte b){
    	int n = b; if(n < 0){
    		n += 256;
    	}
    	int d1 = n / 16;
    	int d2 = n % 16;
    	return hexDigIts[d1] + hexDigIts[d2];
    }

    public static void main(String[] args) {
    	System.out.println(Md5Util.MD5Encode("来一个"));
    }
	public static String getMD5(String str) {
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
