package com.example.room.utils;

import org.junit.Test;

import java.util.*;

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
    public void getRecurringDaysAnotherParam() {
        Calendar calendar = new GregorianCalendar(2019, 1, 20);
        List<Date> recurringDays = DateUtil.getRecurringDays(calendar.getTime(), 2);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(recurringDays.get(0));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(recurringDays.get(1));
        assertEquals(2019, calendar1.get(Calendar.YEAR));
        assertEquals(1, calendar1.get(Calendar.MONTH));
        assertEquals(20, calendar1.get(Calendar.DAY_OF_MONTH));

        assertEquals(2019, calendar2.get(Calendar.YEAR));
        assertEquals(1, calendar2.get(Calendar.MONTH));
        assertEquals(27, calendar2.get(Calendar.DAY_OF_MONTH));

    }
}