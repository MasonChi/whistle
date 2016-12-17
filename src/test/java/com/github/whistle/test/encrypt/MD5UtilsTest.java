package com.github.whistle.test.encrypt;

import com.github.whistle.encrypt.MD5Utils;
import org.junit.Test;

/**
 * Created by child.
 * Date: 2016/12/17.
 * Description:
 */
public class MD5UtilsTest {

    @Test
    public void test() {
        System.out.println(MD5Utils.encrypt("123456"));
    }
}
