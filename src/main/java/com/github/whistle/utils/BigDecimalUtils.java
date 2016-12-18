package com.github.whistle.utils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by child.
 * Date: 2016/12/14.
 * Description: decimal工具类
 */
public class BigDecimalUtils {

    /**
     * 获取List<BigDecimal>中的最大值
     *
     * @param list 查询的BigDecimal列表
     * @return MaxValue
     */
    public static BigDecimal getMaxValue(final List<BigDecimal> list) throws Exception {
        try {
            BigDecimal target = BigDecimal.ZERO;
            final int totalCount = list.size();
            if (totalCount >= 1) {
                BigDecimal max = list.get(0);
                for (int i = 0; i < totalCount; i++) {
                    final BigDecimal temp = list.get(i);
                    if (temp.compareTo(max) > 0) {
                        max = temp;
                    }
                }
                target = max;
            }
            return target;
        } catch (final Exception ex) {
            throw ex;
        }
    }

    /**
     * 获取List<BigDecimal>中的最小值
     *
     * @param list 查询的BigDecimal列表
     * @return minValue
     */
    public static BigDecimal getMinValue(final List<BigDecimal> list) throws Exception {
        try {
            BigDecimal target = BigDecimal.ZERO;
            final int totalCount = list.size();
            if (totalCount >= 1) {
                BigDecimal min = list.get(0);
                for (int i = 0; i < totalCount; i++) {
                    final BigDecimal temp = list.get(i);
                    if (min.compareTo(temp) > 0) {
                        min = temp;
                    }
                }
                target = min;
            }
            return target;
        } catch (final Exception ex) {
            throw ex;
        }
    }
}
