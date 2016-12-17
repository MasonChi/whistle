package com.github.whistle.test.utils;

import com.github.whistle.utils.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by child.
 * Date: 2016/12/17.
 * Description:
 */
public class StringUtilsTest {

    @Test
    public void validateNull() {
        System.out.println(StringUtils.validateNull(null));
        System.out.println(StringUtils.validateNull(1));
    }

    @Test
    public void hasBlank() {
        System.out.println(StringUtils.hasBlank("1", 2, "3", new BigDecimal(1)));
        System.out.println(StringUtils.hasBlank("1", "null", "3", new BigDecimal(1)));
        System.out.println(StringUtils.hasBlank("1", "", "3", new BigDecimal(1)));
        System.out.println(StringUtils.hasBlank("1", null, "3", new BigDecimal(1)));
    }

    @Test
    public void isBlank() {
        System.out.println(StringUtils.isBlank("1", 2, "3", new BigDecimal(1)));
        System.out.println(StringUtils.isBlank("1", "null", "3", new BigDecimal(1)));
        System.out.println(StringUtils.isBlank("1", "", "3", new BigDecimal(1)));
        System.out.println(StringUtils.isBlank("1", null, "3", new BigDecimal(1)));
        System.out.println(StringUtils.isBlank("", null));
    }

    @Test
    public void isNotBlank() {
        System.out.println(StringUtils.isNotBlank("1", 2, "3", new BigDecimal(1)));
        System.out.println(StringUtils.isNotBlank("1", "null", "3", new BigDecimal(1)));
        System.out.println(StringUtils.isNotBlank("1", "", "3", new BigDecimal(1)));
        System.out.println(StringUtils.isNotBlank("1", null, "3", new BigDecimal(1)));
        System.out.println(StringUtils.isNotBlank("", null));
    }


    @Test
    public void mapToGet() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "child");
        map.put("age", 20);

        String getParam = StringUtils.mapToGet(map);
        System.out.println(getParam);
    }

    @Test
    public void getToMap() {
        String get = "?name=child&age=20";
        Map<String, Object> map = StringUtils.getToMap(get);
        System.out.println(map.get("name"));
        System.out.println(map.get("age"));
    }
}
