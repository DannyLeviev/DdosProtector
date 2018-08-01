package com.mynextcomp.DdosProtector.services;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.mynextcomp.DdosProtector.repository.HttpClientCache;

@Service
public class DdosProtectorService {

	public boolean servClientRequest(String clientID) throws ExecutionException {
		return HttpClientCache.get(clientID).incrementAndGet() < 6;
	}

}
