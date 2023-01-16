package pub.wii.cook.java.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    public static LocalDate tsToBeijingDate(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneId.of("Asia/Shanghai")).toLocalDate();
    }

    public static void main(String[] args) {
        DateTimeFormatter fm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        long ts = System.currentTimeMillis();
        Date date = new Date(ts);
        //        TimeStamp tsn = new TimeStamp(ts);
        LocalDate dt = DateUtils.tsToBeijingDate(ts);
        System.out.println(DATE_FORMAT.format(date));
        System.out.println(dt.format(fm));
    }
}
