package com.github.whistle.test.encrypt;

import com.github.whistle.encrypt.AESUtils;

/**
 * Created by child.
 * Date: 2016/12/17.
 * Description:
 */
public class AESUtilsTest {

    public static void main(String[] args) {
        try {
            String content = "helloworld";
            String password = "1234567891234567";
            String result = AESUtils.encrypt(content, password);
            System.out.println(result);

            System.out.println(AESUtils.decrypt(result, password));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
