package com.example.room.utils;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class DateUtilTest {

    @Test
    public void 정상출력인경우() throws Exception {
        List<Integer> integers = DateUtil.extractList(0, 0, 0, 30);
        for (int i : integers) {
            assertEquals(0, i);
        }

    }

    @Test(expected = Exception.class)
    public void 시작시간이뒤인경우() throws Exception {
        List<Integer> integers = DateUtil.extractList(1, 0, 0, 30);
        for (int i : integers) {
            assertEquals(0, i);
        }
    }

    @Test(expected = Exception.class)
    public void 시간단위가틀린경우() throws Exception {
        List<Integer> integers = DateUtil.extractList(25, 0, 26, 30);
        for (int i : integers) {
            assertEquals(0, i);
        }
    }

    @Test(expected = Exception.class)
    public void 시간단위가음수인경우() throws Exception {
        List<Integer> integers = DateUtil.extractList(-2, 0, 12, 30);
        for (int i : integers) {
            assertEquals(0, i);
        }
    }

    @Test(expected = Exception.class)
    public void 시간단위가유닛단위가아닌경우() throws Exception {
        List<Integer> integers = DateUtil.extractList(10, 28, 12, 30);
        for (int i : integers) {
            assertEquals(0, i);
        }
    }

    @Test
    public void 리스트값이여러개인경우() throws Exception {
        List<Integer> integers = DateUtil.extractList(8, 30, 12, 0);
        List<Integer> expected = Arrays.asList(850, 900, 950, 1000, 1050, 1100, 1150);
        assertThat(integers, is(expected));
    }


    @Test
    public void getRecurringDays() {
        List<Date> recurringDays = DateUtil.getRecurringDays(2019, 1, 19, 2, 7);
        SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
        assertEquals("2019/01/19", date.format(recurringDays.get(0)));
        assertEquals("2019/01/26", date.format(recurringDays.get(1)));
    }
}