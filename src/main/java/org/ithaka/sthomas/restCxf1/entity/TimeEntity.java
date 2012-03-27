package org.ithaka.sthomas.restCxf1.entity;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.format.datetime.DateFormatter;

public class TimeEntity {
	private Date theTime;
	private TimeZone tz;
	private int id;
	
	public TimeEntity() {
		theTime = new Date();
		tz = TimeZone.getDefault();
	}
	
	public TimeEntity(Date date) {
		theTime = date;
		tz = TimeZone.getDefault();
	}

	public Date getTime() {
		return theTime;
	}

	public void setTime(Date theTime) {
		this.theTime = theTime;
	}

	public TimeZone getTz() {
		return tz;
	}

	public void setTz(TimeZone tz) {
		this.tz = tz;
	}
	
	@Override
	public String toString() {
		DateFormatter formatter = new DateFormatter("MM/dd/yyyy hh:mm:ss");
		formatter.setTimeZone(tz);
		return formatter.print(theTime, Locale.getDefault());
	}
	
	public String getDateString() {
		return toString();
	}

	public void setId(int thisId) {
		id = thisId;
	}
	
	public int getId() {
		return id;
	}
}
