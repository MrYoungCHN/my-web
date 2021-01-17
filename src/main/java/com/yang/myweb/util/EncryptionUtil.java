package com.yang.myweb.util;


import org.springframework.security.crypto.codec.Hex;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * PBKDF2加密算法
 *
 * @author Daze
 * @date 2021-01-11
 */
public class EncryptionUtil {
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    /**
     * 密文长度
     */
    public static final int HASH_SIZE = 32;

    /**
     * 迭代次数
     */
    public static final int PBKDF2_ITERATIONS = 10000;

    /**
     * 根据password和salt生成密文
     */
    public static String getCiphertext(String password, String salt) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        //将16进制字符串形式的salt转换成byte数组
        byte[] bytes = Hex.decode(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), bytes, PBKDF2_ITERATIONS, HASH_SIZE * 4);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        byte[] hash = secretKeyFactory.generateSecret(spec).getEncoded();
        //将byte数组转换为16进制的字符串
        return new String(Hex.encode(hash));
    }
}
