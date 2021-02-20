package br.com.healthtrack.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {
	
	public static String encrypt(String password) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes("ISO-8859-1"));
			BigInteger hash= new BigInteger(1, md.digest());
			return hash.toString(16); 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return password;
	}
}