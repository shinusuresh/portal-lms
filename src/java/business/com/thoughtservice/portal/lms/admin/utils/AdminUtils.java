package com.thoughtservice.portal.lms.admin.utils;

import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.codec.Base64;

public class AdminUtils {

	private static final Log LOGGER = LogFactory.getLog(AdminUtils.class);

	private static final Random RANDOM = new SecureRandom();
	/** Length of password. @see #generateRandomPassword() */
	public static final int PASSWORD_LENGTH = 8;

	// Pick from some letters that won't be easily mistaken for each
	// other. So, for example, omit o O and 0, 1 l and L.
	private static final String LETTERS = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

	// some random salt
	private static final byte[] SALT = { (byte) 0x21, (byte) 0x21, (byte) 0xF0,
			(byte) 0x55, (byte) 0xC3, (byte) 0x9F, (byte) 0x5A, (byte) 0x75 };

	private final static int ITERATION_COUNT = 31;

	/**
	 * Generate a random String suitable for use as a temporary password.
	 *
	 * @return String suitable for use as a temporary password
	 * @since 2.4
	 */
	public static String generateRandomPassword() {

		String pw = "";
		for (int i = 0; i < PASSWORD_LENGTH; i++) {
			int index = (int) (RANDOM.nextDouble() * LETTERS.length());
			pw += LETTERS.substring(index, index + 1);
		}
		return pw;
	}

	/**
	 * Encode a string input.
	 * 
	 * @param input
	 * @return
	 */
	public static String encode(String input) {
		if (input == null) {
			throw new IllegalArgumentException();
		}
		try {

			KeySpec keySpec = new PBEKeySpec(null, SALT, ITERATION_COUNT);
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT,
					ITERATION_COUNT);

			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);

			Cipher ecipher = Cipher.getInstance(key.getAlgorithm());
			ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

			byte[] enc = ecipher.doFinal(input.getBytes());

			String res = new String(Base64.encode(enc));
			// escapes for url
			res = res.replace('+', '-').replace('/', '_').replace("%", "%25")
					.replace("\n", "%0A");

			return res;

		} catch (Exception e) {
			LOGGER.error(e);
		}

		return "";

	}

	/**
	 * Decode a string input
	 * 
	 * @param token
	 * @return
	 */
	public static String decode(String token) {
		if (token == null) {
			return null;
		}
		try {

			String input = token.replace("%0A", "\n").replace("%25", "%")
					.replace('_', '/').replace('-', '+');

			byte[] dec = Base64.decode(input.getBytes());

			KeySpec keySpec = new PBEKeySpec(null, SALT, ITERATION_COUNT);
			AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT,
					ITERATION_COUNT);

			SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
					.generateSecret(keySpec);

			Cipher dcipher = Cipher.getInstance(key.getAlgorithm());
			dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

			byte[] decoded = dcipher.doFinal(dec);

			String result = new String(decoded);
			return result;

		} catch (Exception e) {
			LOGGER.error(e);
		}

		return null;
	}

}
