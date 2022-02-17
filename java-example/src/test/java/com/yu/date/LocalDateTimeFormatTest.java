package com.yu.date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * http://tutorials.jenkov.com/java-date-time/parsing-formatting-dates.html
 * @author yuhangbin
 * @date 2022/2/17
 **/
public class LocalDateTimeFormatTest {

	@Test
	void testLocalDateTime() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime localDateTime = LocalDateTime.of(2022, 3, 10, 10, 10, 10);
		String except = "2022-03-10 10:10:10.000";
		Assertions.assertEquals(except, localDateTime.format(dateTimeFormatter));
	}
}
