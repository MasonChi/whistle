package com.github.whistle.test.utils;

import com.github.whistle.test.obj.Param;
import com.github.whistle.utils.HttpRequestUtils;
import org.junit.Test;

/**
 * Created by child.
 * Date: 2016/12/18.
 * Description:
 */
public class HttpConnectionUtilsTest {

    @Test
    public void sendGet() {
        String result = HttpRequestUtils.sendGet(Param.URL + "/" + Param.GET_URI, null, "UTF-8");
        System.out.println(result);
    }

    @Test
    public void sendPost() {

        String result = HttpRequestUtils.sendGet(Param.URL + "/" + Param.GET_URI, null, "UTF-8");
        System.out.println(result);
    }
}
