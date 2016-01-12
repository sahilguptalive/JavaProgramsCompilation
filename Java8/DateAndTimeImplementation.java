package Java8;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateAndTimeImplementation {

    public static void main(String[] args) {

        //Instant class is the easiest class to locate a single instantaneous point on time scale.
        //it stores both epoch time and nano-seconds for a particular instant.
        Instant instant = Instant.now();
        print("current epoch time in millis: " + instant.toEpochMilli());
        print("current nano seconds: " + instant.getNano());

        //date and time classes which time zone independent and would work on local time zone only
        //LocalDateTime is an immutable date-time object that represents a date-time, often viewed as year-month-day-hour-minute-second.
        LocalDateTime time = LocalDateTime.now();
        printTime(time);

        //LocalDateTime is immutable, so there are no setters and new class would be created using with methods.
        LocalDateTime modifiedTime = time.withMonth(3).withDayOfMonth(10).withHour(3);
        printTime(modifiedTime);

        //if some timezone dependent date time is required, we can use - ZonedDateTime
        //ZonedDateTime is an immutable representation of a date-time with a time-zone
        ZonedDateTime zonedTimeEurope = ZonedDateTime.of(time, ZoneId.of("Europe/Paris"));
        printTime(zonedTimeEurope);

        //modify the local date time
        final ZoneId zoneIdIndia = ZoneId.of("Asia/Kolkata");
        ZonedDateTime zonedTimeIndia = zonedTimeEurope.withZoneSameInstant(zoneIdIndia);
        printTime(zonedTimeIndia);

        zonedTimeIndia = zonedTimeEurope.withZoneSameLocal(zoneIdIndia);
        printTime(zonedTimeIndia);

        zonedTimeIndia = zonedTimeEurope.withFixedOffsetZone();
        printTime(zonedTimeIndia);
    }

    private static void print(String msg) {

        System.out.println(msg);
    }

    private static void printTime(LocalDateTime time) {
        print("========Local Date Time=========");
        print(time.toString());
        print("local data:" + time.toLocalDate());
        print("local time:" + time.toLocalTime());
    }

    private static void printTime(ZonedDateTime time) {
        print("======Zoned Date Time=====");
        print(time.toString());
    }
}
