package com.github.whistle.encrypt;

import org.apache.commons.lang3.CharEncoding;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by child.
 * Date: 2016/12/17.
 * Description:MD5加密工具类
 */
public class MD5Utils {

    /**
     * Md5加密
     *
     * @param text 明文
     * @return 加密后的字符串
     */
    public static String encrypt(final String text) {
        if (text == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(text.getBytes(CharEncoding.UTF_8));

            final byte[] byteArray = messageDigest.digest();

            final StringBuffer md5StrBuff = new StringBuffer();

            for (final byte element : byteArray) {
                if (Integer.toHexString(0xFF & element).length() == 1) {
                    md5StrBuff.append("0").append(
                            Integer.toHexString(0xFF & element));
                } else {
                    md5StrBuff.append(Integer.toHexString(0xFF & element));
                }
            }

            return md5StrBuff.toString();
        } catch (final NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
        } catch (final UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
