package com.example.room.utils;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DateUtilTest {

    @Test
    public void extractList_정상출력인경우() throws Exception {
        List<Integer> integers = DateUtil.extractList(0, 0, 0, 30);
        assertEquals(1, integers.size());
    }

    @Test
    public void extractList_정상출력인경우2() throws Exception {
        List<Integer> integers = DateUtil.extractList(0, 0, 1, 30);
        assertEquals(3, integers.size());
    }

    @Test
    public void extractList_정상출력인경우3() throws Exception {
        List<Integer> integers = DateUtil.extractList(13, 30, 14, 00);
        assertEquals(1, integers.size());
    }

    @Test(expected = Exception.class)
    public void extractList_비정상_시작시간이뒤인경우() throws Exception {
        List<Integer> integers = DateUtil.extractList(1, 0, 0, 30);
        assertEquals(0, integers.size());
    }

    @Test(expected = Exception.class)
    public void extractList_비정상_시간단위가틀린경우() throws Exception {
        List<Integer> integers = DateUtil.extractList(25, 0, 26, 30);
        assertEquals(0, integers.size());
    }

    @Test(expected = Exception.class)
    public void extractList_시간단위가음수인경우() throws Exception {
        List<Integer> integers = DateUtil.extractList(-2, 0, 12, 30);
        assertEquals(0, integers.size());
    }

    @Test(expected = Exception.class)
    public void extractList_시간단위가유닛단위가아닌경우() throws Exception {
        List<Integer> integers = DateUtil.extractList(10, 28, 12, 30);
        assertEquals(0, integers.size());
    }

    @Test
    public void getRecurringDays_정상인경우() throws Exception {
        List<String> recurringDays = DateUtil.getRecurringDays("01/25/2019", 3);
        assertEquals("01/25/2019", recurringDays.get(0));
        assertEquals("02/01/2019", recurringDays.get(1));
        assertEquals("02/08/2019", recurringDays.get(2));
    }

    @Test(expected = Exception.class)
    public void getRecurringDays_시간값이비정상() throws Exception {
        List<String> recurringDays = DateUtil.getRecurringDays("014495", 3);
    }

    @Test(expected = Exception.class)
    public void getRecurringDays_시간값이비정상2() throws Exception {
        List<String> recurringDays = DateUtil.getRecurringDays("014/495", 3);
    }

    @Test
    public void getStartTimeAndEndTime() {
        String startTime = DateUtil.getStartTime("01/25/2019", 150);
        assertEquals("2019-01-25 01:30", startTime);

        String endTime = DateUtil.getEndTime("01/25/2019", 150);
        assertEquals("2019-01-25 02:00", endTime);
    }
}