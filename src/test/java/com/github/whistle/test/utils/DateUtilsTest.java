package com.github.whistle.test.utils;

import com.github.whistle.utils.DateUtils;
import com.github.whistle.utils.enums.Portion;
import org.junit.Test;

import java.util.Date;

/**
 * Created by child.
 * Date: 2016/12/18.
 * Description:
 */
public class DateUtilsTest {

    @Test
    public void getQuarter() {
        System.out.println(DateUtils.getQuarter(1));
        System.out.println(DateUtils.getQuarter(2));
        System.out.println(DateUtils.getQuarter(4));
        //System.out.println(DateUtils.getQuarter(0));
        //System.out.println(DateUtils.getQuarter(13));
        //System.out.println(DateUtils.getQuarter(-1));
    }

    @Test
    public void getPortion() {
        System.out.println(DateUtils.getPortion(new Date(), Portion.YEAR));
        System.out.println(DateUtils.getPortion(new Date(), Portion.MONTH));
        System.out.println(DateUtils.getPortion(new Date(), Portion.DAY));
    }
}
