package com.sonymm.bxwtfk.util;

import java.security.MessageDigest;

/**
 * @ClassName: GetStringMD5Util
 * @Description: 字符串MD5生成工具類
 * @author sjx
 * @date 2016年04月03日 上午11:00:42
*/
public class GetStringMD5Util {

	private GetStringMD5Util() {
        throw new Error("禁止实例化");
    }
	public static String getMD5(String str){
		try {  
            // 获得MD5摘要算法的 MessageDigest对象  
            MessageDigest mdInst = MessageDigest.getInstance("MD5");  
            // 使用指定的字节更新摘要  
            mdInst.update(str.getBytes());  
            // 获得密文  
            byte[] md = mdInst.digest();  
            // 把密文转换成十六进制的字符串形式  
            StringBuffer buf = new StringBuffer();  
            for (int i = 0; i < md.length; i++) {  
                int tmp = md[i];  
                if (tmp < 0)  
                    tmp += 256;  
                if (tmp < 16)  
                    buf.append("0");  
                buf.append(Integer.toHexString(tmp));  
            }  
//            return buf.toString().substring(8, 24);// 16位加密  
            return buf.toString();// 32位加密  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
	}
}
