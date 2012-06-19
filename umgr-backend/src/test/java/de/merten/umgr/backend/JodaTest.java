package de.merten.umgr.backend;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.junit.Test;

public class JodaTest {

	@Test
	public void testJoda() {
		
		Date date = new Date();
		
		DateTime dt = new DateTime(date.getTime());
		LocalDateTime ldt = new LocalDateTime(date.getTime());
		
		int dayOfWeek = dt.getDayOfWeek();
		int hours = dt.getHourOfDay();
		int minutes = dt.getMinuteOfHour();
		int seconds = dt.getSecondOfMinute();
		int millis = dt.getMillisOfSecond();
		
		System.out.println("Date: " + date);
		System.out.println("DateTime: " + dt);
		System.out.println("LocalDateTime: " + ldt);
		
		System.out.println("DOW: " + dayOfWeek);
		System.out.println("Hours: " + hours);
		System.out.println("Minutes: " + minutes);
		System.out.println("Seconds: " + seconds);
		System.out.println("Millis: " + millis);
		
		System.out.println("millis added: " + (hours*60*60*1000 + minutes*60*1000 + seconds*1000 + millis));
		
		System.out.println("millis of day: " + (dt.getMillisOfDay()));
		
		// cool ---> millis of day sind also die millisekunden der uhrzeit
		
	}
}
