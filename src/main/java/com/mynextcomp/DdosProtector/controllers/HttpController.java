package com.mynextcomp.DdosProtector.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private final static Logger LOGGER = LoggerFactory.getLogger(HttpController.class);

	@Autowired
	public HttpController(DdosProtectorService ddosProtServ) {
		this.ddosProtServ = ddosProtServ;
	}

	@RequestMapping()
	public ResponseEntity<Void> getRequest(@RequestParam("clientID") String clientID) {
		LOGGER.info(
				"HttpController recognised the request mapping and the getRequest() was called with the parameter {}.",
				clientID);
		HttpStatus httpStatus;
		try {
			if (ddosProtServ.servClientRequest(clientID)) {
				LOGGER.info("DdosProtectorService.servClientRequest() returned TRUE for clientID = {}.", clientID);
				httpStatus = HttpStatus.OK;
			} else {
				LOGGER.warn("DdosProtectorService.servClientRequest() returned FALSE for clientID = {}.", clientID);
				httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
			}
		} catch (Exception e) {
			LOGGER.error("Calling to the ddosProtServ.servClientRequest(clientID) failed ! {}", e);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpStatus);
	}

}
