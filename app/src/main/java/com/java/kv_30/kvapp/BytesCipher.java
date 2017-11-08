package com.java.kv_30.kvapp;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by TanyaOhotnik on 08.11.2017.
 */

public class BytesCipher {
    //AES only supports key sizes of 16, 24 or 32 bytes, size of iv and key must be similar
    private static byte[] iv = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    public static byte[] encryptBytesWithAES(byte[] fileBytes, String key) {

//         Cipher cipher = Cipher.getInstance ("AES/CBC/PKCS5Padding", "BC");
        try {
            Cipher c = Cipher.getInstance("AES/CTR/NoPadding");
            SecretKey skey = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            c.init(Cipher.ENCRYPT_MODE, skey, ivSpec);
            return c.doFinal(fileBytes);
        } catch (Exception e) {
        }
        return null;
    }

    public static byte[] decryptBytesWithAES(byte[] fileBytes, String key) {
        try {
            Cipher c = Cipher.getInstance("AES/CTR/NoPadding");
            SecretKey skey = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            c.init(Cipher.DECRYPT_MODE, skey, ivSpec);
            return c.doFinal(fileBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
