package com.mynextcomp.DdosProtector.services;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;

import com.mynextcomp.DdosProtector.model.ClientCallsPool;
import com.mynextcomp.DdosProtector.repository.HttpClientCache;

@Service
public class DdosProtectorService {

	private final ReentrantLock lock = new ReentrantLock();

	public boolean servClientRequest(String clientID) throws ExecutionException {
		boolean result = false;
		ClientCallsPool clientCallsPool = HttpClientCache.getClientCallsPool(clientID);
		lock.lock();
		try {
			if (clientCallsPool.canServe()) {
				// reset the timer of 5 sec in the cache
				HttpClientCache.put(clientID, clientCallsPool);
				result = true;
			}
		} finally {
			lock.unlock();
		}
		return result;
	}
}
