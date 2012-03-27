package org.ithaka.sthomas.restCxf1.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.ithaka.sthomas.restCxf1.entity.TimeEntity;
import org.ithaka.sthomas.restCxf1.rest.Resource;
import org.ithaka.sthomas.restCxf1.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/time/*")
public class TimeController extends BaseController<Resource<TimeEntity>> {

	@Autowired
	private TimeService timeService;
 
	@RequestMapping(consumes="application/json", produces="application/json", method=RequestMethod.POST, value="/")
	public ResponseEntity<Resource<TimeEntity>> dynamicTest(@RequestBody TimeEntity order){
		timeService.create(order);
		Resource<TimeEntity> resource = new Resource<TimeEntity>(order);
		return new ResponseEntity<Resource<TimeEntity>>(resource,HttpStatus.CREATED);
	}
 
	@RequestMapping(consumes="application/json", produces="application/json", method=RequestMethod.GET, value="/{id}")
	public ResponseEntity<Resource<TimeEntity>> getOrder(@PathVariable("id") String id){
		TimeEntity order = timeService.find(id);
		Resource<TimeEntity> resource = new Resource<TimeEntity>(order);
		return new ResponseEntity<Resource<TimeEntity>>(resource,HttpStatus.OK);
	}
 
	@RequestMapping(consumes="application/json", produces="application/json", method=RequestMethod.PUT, value="/{id}")
	public ResponseEntity<Resource<TimeEntity>> update(@RequestBody TimeEntity order){
		order = timeService.update(order);
		Resource<TimeEntity> resource = new Resource<TimeEntity>(order);
		return new ResponseEntity<Resource<TimeEntity>>(resource,HttpStatus.NO_CONTENT);
	}
 
	@Override
	public ResponseEntity<Resource<TimeEntity>> describe(HttpServletRequest request) {
		//TODO describe this resource links
		return null;
	}


}
