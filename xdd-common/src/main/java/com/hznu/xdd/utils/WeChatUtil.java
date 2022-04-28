package com.hznu.xdd.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;

/**
 * @author ：引自 简书
 * @url: https://www.jianshu.com/p/3170749b6660
 */
public class WeChatUtil {
    public static void main(String[] args) throws BadPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, InvalidParameterSpecException, NoSuchPaddingException, UnsupportedEncodingException, NoSuchProviderException {
        String result = decryptNew(
                "ypRlHoT7RnHYI/lcj5a5ByF0d3VQ2Qz9N6wKNI654mhGePYZotikvl7QUow5lp70OjQpQuqVbwgEWanqqq1uvq/HJ8EO2QIqWflFsEytfKgdl7rLjqt/2IRm3MUs9bwaWHJ0ZoYuIQEx4H2Nr+q8Jy83CYtwsA3Epy0coP8ldcHkD8kObzwewQmhljXjdHMOlSzdH3A4PAOmsa6NE5EOtLRUgmQbPGKqPH0cHfv9PhtM5UfNnrkJxEN6WOfrTwvpI8l1zovkutT6lXkBwZOunhdrlNOxoMiK3zHk+7TKkY1Xi199QaCU8RsuKOVFIgr7lnlrB42kc+OCNdoxM27sxuvUA1FiU1GHM860fasXQm3P8VLpDU2dwlP6MTO8+X4UeWjir84XRa4SgHTf47wZTQ==",
                "71lVvBwAEf6auK07E0ktCg==",
                "0L1vU0x/a2OM6BZTdcewzA=="
        );
        System.out.println("result = " + result);
    }

    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
    private static Key key;
    private static Cipher cipher;

    public static String decryptData(String encryptDataB64, String sessionKeyB64, String ivB64) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidParameterSpecException, BadPaddingException, InvalidKeyException {
        return new String(
                decryptOfDiyIV(
                        Base64.decode(encryptDataB64),
                        Base64.decode(sessionKeyB64),
                        Base64.decode(ivB64)
                )
        );
    }

    private static void init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes      解密密钥
     * @param ivs           自定义对称解密算法初始向量 iv
     * @return 解密后的字节数组
     */
    private static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes, byte[] ivs) throws InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidParameterSpecException, BadPaddingException, InvalidKeyException {
        byte[] encryptedText;
        init(keyBytes);
        try {
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivs));
            cipher.init(Cipher.DECRYPT_MODE, key, parameters);
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
           throw e;
        }
        return encryptedText;
    }

    private static String decryptNew(String encryptedData, String sessionKey, String iv) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidParameterSpecException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        String result = "";
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
            // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                result = new String(resultByte, "UTF-8");
            }

        return result;
    }
}
