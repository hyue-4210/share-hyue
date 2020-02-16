package com.powerlong.sharera.util;

import java.util.Random;
import java.util.UUID;

public class RandomUtils {
	  // 随机字符串生成  
    public static String getRandomString(int length) { // length表示生成字符串的长度  
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            int number = random.nextInt(base.length());  
            sb.append(base.charAt(number));  
        }  
        return sb.toString();  
    }
	  // 随机字符串生成  
    public static String getRandomNumber(int length) { // length表示生成字符串的长度  
        String base = "0123456789";  
        Random random = new Random();  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            int number = random.nextInt(base.length());  
            sb.append(base.charAt(number));  
        }  
        return sb.toString();  
    }



//    public static String getUUID() {
//        int machineId = 1;//最大支持1-9个集群机器部署
//        int hashCodeV = UUID.randomUUID().toString().hashCode();
//        if(hashCodeV < 0) {//有可能是负数
//            hashCodeV = - hashCodeV;
//        }
//        return machineId + String.format("%015d", hashCodeV);
//    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static void main(String[] args) {
    	System.out.println(RandomUtils.getUUID());
    }
}
