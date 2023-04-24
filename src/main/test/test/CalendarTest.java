package test;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarTest {
    @Test
    void calendarTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(date);
        //获得本月第一天
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = sdf.format(calendar.getTime());
        System.out.println("firstDay:" + firstDay);
        //获得本月最后一天
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String lastDay = sdf.format(calendar.getTime());
        System.out.println("lastDay:" + lastDay);
    }
}
