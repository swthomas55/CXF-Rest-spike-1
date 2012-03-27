package org.ithaka.sthomas.restCxf1.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.ithaka.sthomas.restCxf1.rest.ETagService;
import org.ithaka.sthomas.restCxf1.rest.InvalidTagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public abstract  class BaseController<T> {

	@Autowired
	protected ETagService eTagService;

//	@ExceptionHandler(EntityNotFoundException`.class)
	protected ResponseEntity<T> entityNotFound() {
		return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.HEAD, value = "/{id}")
	public ResponseEntity<T> head(HttpServletRequest request) {
		String url = ServletUriComponentsBuilder.fromRequest(request).build()
				.toString();
		try {
			String tag = eTagService.get(url);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Etag", tag);
			return new ResponseEntity<T>(null, headers, HttpStatus.NO_CONTENT);
		} catch (InvalidTagException e) {
			return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(consumes = "application/json", produces = "application/json", method = RequestMethod.OPTIONS, value = "/")
	public abstract ResponseEntity<T> describe(HttpServletRequest request);
}
