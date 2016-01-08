import java.time.*;

public class Java8DateAndTime{

	public static void main(String[] args){

		//date and time classes which time zone independent and would work on local time zone only
		//LocalDateTime is an immutable date-time object that represents a date-time, often viewed as year-month-day-hour-minute-second.
		LocalDateTime time=LocalDateTime.now();
		printTime(time);	
		//LocalDateTime is immutable, so there are no setters and new class would be created using with methods.
		LocalDateTime modifiedTime=time.withMonth(3).withDayOfMonth(10).withHour(3);
		printTime(modifiedTime);
		//if some timezone dependent date time is required, we can use - ZonedDateTime
		//ZonedDateTime is an immutable representation of a date-time with a time-zone
		ZonedDateTime zonedTime=ZonedDateTime.of(time,ZoneId.of("Europe/Paris"));
		printTime(zonedTime);
		//modify the local date time
		ZonedDateTime diffLocalWithZone=zonedTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
		printTime(diffLocalWithZone);
	}
	private static void print(String msg){

		System.out.println(msg);
	}
	private static void printTime(LocalDateTime time){
		print("========Local Date Time=========");
		print(time.toString());
		print(time.toLocalDate().toString());
		print(time.toLocalTime().toString());
	}
	private static void printTime(ZonedDateTime time){
		print("======Zoned Date Time=====");
		print(time.toString());
	}
}
