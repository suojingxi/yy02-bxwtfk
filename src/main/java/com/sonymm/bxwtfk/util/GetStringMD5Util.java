package com.sonymm.bxwtfk.util;

import java.security.MessageDigest;


/**
 * @ClassName: GetStringMD5Util
 * @Description: 字符串MD5生成工具類
 * @author sjx
 * @date 2016年04月03日 上午11:00:42
*/
public class GetStringMD5Util {

	final static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7',
		'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * Encodes a string
	 * 
	 * @param str
	 *            String to encode
	 * @return Encoded String
	 */
	public static String crypt(String str) {
		return crypt(str, Charsets.UTF8.encoding);
	}
	
	/**
	 * encode a string with given encoding
	 * 
	 * @param str
	 *            String to encode
	 * @param charSet
	 *            encoding
	 * @return the encode String
	 */
	public static String crypt(String str, String charSet) {
		if (str == null || str.length() == 0 || charSet == null) {
			throw new IllegalArgumentException(
					"String or charset to encript cannot be null or zero length");
		}
		return getMD5(Charsets.getBytes(str, charSet));
	}
	
	public static String getMD5(byte[] source) {
		return crypt(source);
	}
	
	/**
	 * encode bytes
	 * 
	 * @param source
	 *            source bytes
	 * @return the encode String
	 */
	public static String crypt(byte[] source) {
		String s = null;
	
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest();
			char str[] = new char[16 * 2];
			int k = 0;
			for (int i = 0; i < 16; i++) {
				byte byte0 = tmp[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
	
				str[k++] = hexDigits[byte0 & 0xf];
			}
			s = new String(str);
	
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return s;
	}
	
//	public static void main(String[] args) {
//	    System.out.println(GetStringMD5Util.crypt("5000000102211695395800678458941462944795078单据填写问题"));
//	  }
}
