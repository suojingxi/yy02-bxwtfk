package com.sonymm.bxwtfk.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @ClassName: DesUtils
 * @Description: 加密 解密
 * @author sjx
 * @date 2016年04月02日 早晨1:30:20
 */
public class DesUtils {
	private static Key key;
	private static String KEY_STR = "@#^^1234qaz";
	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(KEY_STR.getBytes());
			generator.init(secureRandom);
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException("");
		}
	}

	/**
	 * 加密
	 * 
	 * @param str
	 * @return
	 */

	public static String getEncryptString(String str) {
		BASE64Encoder base64Encoder = new BASE64Encoder();

		try {
			byte[] strBytes = str.getBytes("UTF-8");
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] entryptStrBytes = cipher.doFinal(strBytes);
			return base64Encoder.encode(entryptStrBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 解密
	 * 
	 * @param str
	 * @return
	 */
	public static String getDecryptString(String str) {
		BASE64Decoder base64Decoder = new BASE64Decoder();
		try {
			byte[] strBytes = base64Decoder.decodeBuffer(str);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return new String(encryptStrBytes, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	 public static void main(String[] args) {
	 while (true) {
	 System.out.print("请输入：");
	 BufferedReader input = new BufferedReader(new InputStreamReader(
	 System.in));
	 String inputStr = null;
	 try {
	 inputStr = input.readLine();
	 } catch (IOException e) {
	 e.printStackTrace();
	 }
	 if (inputStr == null || "".equals(inputStr)) {
	 System.out.println("输入为空。");
	 } else {
	 String encryInput = getEncryptString(inputStr);
	 System.out.println("加密后：" + encryInput);
	 }
	 }
	 }
}
