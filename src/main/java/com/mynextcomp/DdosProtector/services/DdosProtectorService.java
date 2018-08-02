package com.mynextcomp.DdosProtector.services;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mynextcomp.DdosProtector.repository.HttpClientCache;

@Service
public class DdosProtectorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DdosProtectorService.class);

	public boolean servClientRequest(String clientID) throws ExecutionException {
		LOGGER.info("DdosProtectorService.servClientRequest() was called for clientID = {}", clientID);
		return HttpClientCache.get(clientID).incrementAndGet() < 6;
	}

}
