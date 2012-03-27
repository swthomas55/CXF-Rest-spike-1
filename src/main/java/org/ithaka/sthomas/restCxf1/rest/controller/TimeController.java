package org.ithaka.sthomas.restCxf1.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
@Path("/time")
@Produces({"application/json"})
@Consumes({"application/json"})
public class TimeController extends BaseController<Resource<TimeEntity>> {

	@Autowired
	private TimeService timeService;

	@POST @Path("/")
	public ResponseEntity<Resource<TimeEntity>> dynamicTest(@RequestBody TimeEntity order){
		timeService.create(order);
		Resource<TimeEntity> resource = new Resource<TimeEntity>(order);
		return new ResponseEntity<Resource<TimeEntity>>(resource,HttpStatus.CREATED);
	}

	@GET @Path("/{id}")
	public ResponseEntity<Resource<TimeEntity>> getOrder(@PathVariable("id") String id){
		TimeEntity order = timeService.find(id);
		Resource<TimeEntity> resource = new Resource<TimeEntity>(order);
		return new ResponseEntity<Resource<TimeEntity>>(resource,HttpStatus.OK);
	}

	@PUT @Path("/{id}")
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
