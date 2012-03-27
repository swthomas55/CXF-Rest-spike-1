package org.ithaka.sthomas.restCxf1.entity;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.hamcrest.number.IsCloseTo;
import org.ithaka.sthomas.restCxf1.entity.TimeEntity;
import org.junit.Test;
import org.springframework.format.datetime.DateFormatter;

public class TimeEntityTest {

	@Test
	public void testDefaultConstructorSetsCurrentTimeAndZone() {
		TimeEntity aTime = new TimeEntity();
		assertThat("Time should be within a few seconds of now", (double)aTime.getTime().getTime(), new IsCloseTo((double)(new Date()).getTime(), 2000.0));
		assertEquals(TimeZone.getDefault(), aTime.getTz());
	}

	@Test
	public void testConstructorSavesGivenTime() throws Exception {
		Date now = new Date();
		TimeEntity aTime = new TimeEntity(now);
		assertSame(now, aTime.getTime());
	}
	
	@Test
	public void testTimeEntityToStringReturnsFormattedDateTime() throws Exception {
		Date then = new Date(123456789012L);
		TimeEntity aTime = new TimeEntity(then);
		String expected = (new DateFormatter("MM/dd/yyyy hh:mm:ss")).print(then, Locale.getDefault());
		assertEquals(expected, aTime.toString());
	}
	
	@Test
	public void testTimeEntityToStringReturnsFormattedGMTDateTime() throws Exception {
		Date then = new Date(123456789012L);
		TimeEntity aTime = new TimeEntity(then);
		aTime.setTz(TimeZone.getTimeZone("GMT"));
		DateFormatter formatter = new DateFormatter("MM/dd/yyyy hh:mm:ss");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		String expected = formatter.print(then, Locale.getDefault());
		assertEquals(expected, aTime.toString());
	}

}
