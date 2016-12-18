package com.github.whistle.test.utils;

import com.github.whistle.test.obj.Param;
import com.github.whistle.utils.HttpConnUtils;
import org.junit.Test;

/**
 * Created by child.
 * Date: 2016/12/18.
 * Description:
 */
public class HttpConnectionUtilsTest {

    @Test
    public void sendGet() {
        String result = HttpConnUtils.sendGet(Param.URL + "/" + Param.GET_URI, null, "UTF-8");
        System.out.println(result);
    }
}
