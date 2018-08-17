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

	private final String SERV_CLIENT_REQUEST_WAS_CALLED_MSG = "DdosProtectorService.servClientRequest() returned {} for clientID = {}.";
	private final String SERV_CLIENT_REQUEST_FAILED_MSG = "Calling to the ddosProtServ.servClientRequest(clientID) failed ! {}";
	private final static Logger LOGGER = LoggerFactory.getLogger(HttpController.class);
	private final DdosProtectorService ddosProtServ;

	@Autowired
	public HttpController(DdosProtectorService ddosProtServ) {
		this.ddosProtServ = ddosProtServ;
	}

	@RequestMapping()
	public ResponseEntity<Void> getRequest(@RequestParam("clientID") String clientID) {
		HttpStatus httpStatus;
		try {
			if (ddosProtServ.servClientRequest(clientID)) {
				LOGGER.info(SERV_CLIENT_REQUEST_WAS_CALLED_MSG, true, clientID);
				httpStatus = HttpStatus.OK;
			} else {
				LOGGER.warn(SERV_CLIENT_REQUEST_WAS_CALLED_MSG, false, clientID);
				httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
			}
		} catch (Exception e) {
			LOGGER.error(SERV_CLIENT_REQUEST_FAILED_MSG, e);
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(httpStatus);
	}

}
