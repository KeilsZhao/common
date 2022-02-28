package com.bzfar.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

@Component
public class DESUtil {

	private static String KEY = "www.com.cn";

	private static String DES = "DES";

	/**
	 * 加密
	 * @param pliantext
	 * @return
	 * @throws Exception
	 */
    public static String encrypt(String pliantext) throws Exception {
        return encodeBase64(encryptDES(pliantext, KEY));
    }
   
    /**
	 * 解密
	 * @param ciphertext
	 * @return
	 * @throws Exception
	 */
    public static String decrypt(String ciphertext) throws Exception {
        return decryptDES(decodeBase64(ciphertext.getBytes()), KEY);
    }
    
    public static String encrypt(String pliantext, String key) throws Exception {
        return encodeBase64(encryptDES(pliantext, key));
    }

    public static String decrypt(String ciphertext, String key) throws Exception {
        return decryptDES(decodeBase64(ciphertext.getBytes()), key);
    }

    private static String encodeBase64(byte[] binaryData) throws Exception {
        try {
            return Base64.encodeBase64String(binaryData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BASE64\u7f16\u7801\u5931\u8d25!");
        }
    }

    private static byte[] decodeBase64(byte[] binaryData) {
        try {
            return Base64.decodeBase64(binaryData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("BASE64\u89e3\u7801\u5931\u8d25\uff01");
        }
    }

    public static byte[] encryptDES(String data, String key) {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            DESKeySpec deskey = new DESKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey secretKey = keyFactory.generateSecret(deskey);
            Cipher cipher = Cipher.getInstance(DES);
            cipher.init(1, secretKey, random);
            return cipher.doFinal(data.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptDES(final byte[] data, final String key) {
        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            Cipher cipher = Cipher.getInstance(DES);
            cipher.init(2, secretKey, random);
            return new String(cipher.doFinal(data), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(final String[] args) throws Exception {
//        String enString = encrypt("{\"clientId\": \"snyh\",\"clientSecret\": \"123456\"}");
//        System.out.println("加密后的字串是：" + enString);
        
        String re = "pkxwAh33T1/9FhhUEUtWREYpR3TjjM2fL4dsLZGRK8I13WF5G7+HoiLw8u6zp6HYl";
        String deString = decrypt(re);
        System.out.println("解密后的字串是：" + deString);
    }
	
}
