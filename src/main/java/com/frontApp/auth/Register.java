package com.frontApp.auth;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Register {
	private static final byte[] encKey
			="AHrNad8WB0yok6KfqYeI2t7yvZJKXe78".getBytes();
	public static String encrypt(String orig) {
		String result = null;
		byte[] cipherText = new byte[0];
		try {
			SecretKey key = new SecretKeySpec(encKey,"AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			cipherText = cipher.doFinal(orig.getBytes());

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(cipherText);
	}
}

