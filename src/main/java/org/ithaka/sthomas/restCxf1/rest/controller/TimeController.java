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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Path("/time")
@Produces({"application/json"})
@Consumes({"application/json"})
public class TimeController extends BaseController<Resource<TimeEntity>> {

	@Autowired
	private TimeService timeService;

	@POST @Path("/")
	public Resource<TimeEntity> create(@RequestBody TimeEntity order){
		timeService.create(order);
		Resource<TimeEntity> resource = new Resource<TimeEntity>(order);
		return resource;
	}

	@GET @Path("/{id}")
	public Resource<TimeEntity> getOrder(@PathVariable("id") String id){
		TimeEntity order = timeService.find(id);
		Resource<TimeEntity> resource = new Resource<TimeEntity>(order);
		return resource;
	}

	@PUT @Path("/{id}")
	public Resource<TimeEntity> update(@RequestBody TimeEntity order){
		order = timeService.update(order);
		Resource<TimeEntity> resource = new Resource<TimeEntity>(order);
		return null;
	}

	@Override
	public ResponseEntity<Resource<TimeEntity>> describe(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}



}
