package com.mynextcomp.DdosProtector.services;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.mynextcomp.DdosProtector.cache.HttpClientCache;

@Service
public class DdosProtectorService {

	// The lock in the next line no need to bee static as DdosProtectorService is a
	// singleton.
	private final Object mutexKey = new Object();

	public boolean servClientRequest(String clientID) throws ExecutionException {
		synchronized (mutexKey) {
			return !(HttpClientCache.get(clientID) == 5);
		}
	}

}
