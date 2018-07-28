package com.mynextcomp.DdosProtector.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mynextcomp.DdosProtector.services.DdosProtectorService;

@RestController
@RequestMapping("")
public class HttpController {

	private DdosProtectorService ddosProtServ;

	@Autowired
	public HttpController(DdosProtectorService ddosProtServ) {
		this.ddosProtServ = ddosProtServ;
	}

	@RequestMapping()
	public ResponseEntity<Void> getRequest(@RequestParam("clientID") String clientID) {
		if (ddosProtServ.validateClientRequest(clientID)) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
	}

}
