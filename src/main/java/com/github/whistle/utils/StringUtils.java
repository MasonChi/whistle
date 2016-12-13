package com.github.whistle.utils;

/**
 * Created by child.
 * Date: 2016/12/13.
 * Description:
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 判断object或多个object是否存在空值元素
     *
     * @param objects 对象数组
     * @return 只要有一个元素为Blank，则返回true
     */
    public static boolean hasBlank(Object... objects) {
        boolean result = false;
        for (Object object : objects) {
            if (object == null || "".equals(object.toString().trim())
                    || "null".equals(object.toString().trim())
                    || "[null]".equals(object.toString().trim())
                    || "[]".equals(object.toString().trim())) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(StringUtils.hasBlank(1));
        System.out.println(StringUtils.hasBlank(1, 2, 3, 4, ""));
    }
}
