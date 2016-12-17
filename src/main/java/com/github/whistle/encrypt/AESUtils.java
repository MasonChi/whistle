package com.github.whistle.encrypt;

import com.github.whistle.utils.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by child.
 * Date: 2016/12/17.
 * Description: AES加密工具类<br/>
 * <p>
 * 采用AES的ECB模式进行加密，填充方式为PKCS5Padding，加密的密码必须为16位。编码方式统一使用UTF-8
 */
public class AESUtils {

    /**
     * 加密
     *
     * @param content 需加密的内容
     * @param key     秘钥
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encrypt(String content, String key) throws Exception {
        if (StringUtils.isBlank(content) || StringUtils.isBlank(key)) {
            return null;
        }

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = cipher.doFinal(content.getBytes("utf-8"));
        return new BASE64Encoder().encode(bytes);
    }

    /**
     * 解密
     *
     * @param content 需解密的内容
     * @param key     秘钥
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decrypt(String content, String key) throws Exception {
        if (StringUtils.isBlank(content) || StringUtils.isBlank(key)) {
            return null;
        }

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes("utf-8"), "AES"));
        byte[] bytes = new BASE64Decoder().decodeBuffer(content);
        bytes = cipher.doFinal(bytes);
        return new String(bytes, "utf-8");
    }
}
