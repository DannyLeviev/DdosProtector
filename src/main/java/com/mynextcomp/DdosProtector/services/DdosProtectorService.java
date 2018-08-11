package com.mynextcomp.DdosProtector.services;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mynextcomp.DdosProtector.repository.HttpClientCache;

@Service
public class DdosProtectorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DdosProtectorService.class);
	private final String SERV_CLIENT_REQUEST_WAS_CALLED_MSG = "DdosProtectorService.servClientRequest() was called for clientID = {}";

	public boolean servClientRequest(String clientID) throws ExecutionException {
		LOGGER.info(SERV_CLIENT_REQUEST_WAS_CALLED_MSG, clientID);
		return HttpClientCache.get(clientID).incrementAndGet() < 6;
	}

}
