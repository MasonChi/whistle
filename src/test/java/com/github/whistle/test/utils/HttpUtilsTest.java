package com.github.whistle.test.utils;

import com.github.whistle.test.obj.GetResponse;
import com.github.whistle.test.obj.Param;
import com.github.whistle.test.obj.PostRequest;
import com.github.whistle.test.obj.PostResponse;
import com.github.whistle.utils.HttpUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;

/**
 * Created by child.
 * Date: 2016/12/16.
 * Description:
 */
public class HttpUtilsTest {

    @Test
    public void isSendGet() {
        boolean isSuccess = HttpUtils.sendGet(Param.URL, Param.GET_URI);
        System.out.println(ToStringBuilder.reflectionToString(isSuccess));
    }

    @Test
    public void sendGet() {
        GetResponse getResponse = HttpUtils.sendGet(Param.URL, Param.GET_URI, GetResponse.class);
        System.out.println(ToStringBuilder.reflectionToString(getResponse));
    }

    @Test
    public void sendPost() {
        PostRequest request = new PostRequest();
        request.setMobile("child");
        PostResponse postResponse = HttpUtils.sendPost(Param.URL, Param.POST_URI,
                request, PostResponse.class);
        System.out.println(ToStringBuilder.reflectionToString(postResponse));
    }
}
