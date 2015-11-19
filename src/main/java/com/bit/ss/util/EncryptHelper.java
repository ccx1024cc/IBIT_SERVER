package com.bit.ss.util;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * @Title: EncryptHelper.java
 * @Package com.bit.ss.util
 * @Description: 加密算法类
 * @author CCX
 * @date 2015年10月11日 下午3:25:15
 * @version V1.0
 */
public class EncryptHelper {
	public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

	// 生成密文密码
	// public static void main(String[] args) {
	// String password = "ccx1024cc";
	// System.out.println("origin pwd : " + password);
	// String encrypted = desEncoding("woshitiancai", password);
	// System.out.println("encrypted : " + encrypted);
	// String decrypted = desDecode("woshitiancai", encrypted);
	// System.out.println("decrypted : " + decrypted);
	// }

	// DES加密
	public static String desEncoding(String key, String data) {
		if (data == null)
			return null;
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			Key secretKey = secretKeyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(1, secretKey, paramSpec);
			byte[] bytes = cipher.doFinal(data.getBytes());
			return byte2hex(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// DES解密
	public static String desDecode(String key, String data) {
		if (data == null)
			return null;
		try {
			DESKeySpec dks = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
			AlgorithmParameterSpec paramSpec = iv;
			cipher.init(2, secretKey, paramSpec);
			return new String(cipher.doFinal(hex2byte(data.getBytes())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	// 二进制转16进制
	private static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();

		for (int n = 0; (b != null) && (n < b.length); n++) {
			String stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

	// 16进制转二进制
	private static byte[] hex2byte(byte[] b) {
		if (b.length % 2 != 0)
			throw new IllegalArgumentException();
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[(n / 2)] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
}