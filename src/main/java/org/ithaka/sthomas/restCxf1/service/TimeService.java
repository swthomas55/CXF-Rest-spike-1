package org.ithaka.sthomas.restCxf1.service;

import java.util.Calendar;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.ithaka.sthomas.restCxf1.entity.TimeEntity;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;

@Service("timeService")
//@Path("/time")
public class TimeService {
	
	AtomicInteger idSeq = new AtomicInteger(0);
	Map<Integer, TimeEntity> db = new ConcurrentHashMap<Integer, TimeEntity>();

//	@GET @Produces("text/plain")
	public String getDateTime()
	{
		DateFormatter formatter = new DateFormatter("dd/MM/yyyy hh:mm:ss");
		return formatter.print(Calendar.getInstance().getTime(), Locale.getDefault());
	}

	public void create(TimeEntity order) {
		int id = idSeq.incrementAndGet();
		order.setId(id);
		db.put(Integer.valueOf(id), order);
	}

	public TimeEntity find(String id2) {
		try {
			int id = Integer.parseInt(id2);
			TimeEntity aTime = db.get(Integer.valueOf(id));
			return aTime;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public TimeEntity update(TimeEntity order) {
		if (order.getId() == 0)
		{
			order.setId(idSeq.incrementAndGet());
		}
		db.put(Integer.valueOf(order.getId()), order);
		return order;
	}
	
	
}
