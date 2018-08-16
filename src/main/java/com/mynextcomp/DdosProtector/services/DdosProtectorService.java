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
				// reset the counter of 5 sec of caching
				HttpClientCache.put(clientID, clientCallsPool);
				result = true;
			}
		} finally {
			lock.unlock();
		}
		return result;
	}
}
