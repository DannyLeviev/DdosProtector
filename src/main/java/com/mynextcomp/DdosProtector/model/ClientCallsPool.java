package com.mynextcomp.DdosProtector.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClientCallsPool {

	private final List<Long> callsPool = new ArrayList<>();

	public synchronized boolean canServe() {
		boolean resalt = false;
		Long currentTime = Instant.now().getEpochSecond();
		// clean older than 5 sec calls
		Iterator<Long> itr = callsPool.iterator();
		while (itr.hasNext()) {
			if (currentTime - itr.next() > 5) {
				itr.remove();
			}
		}
		if (callsPool.size() < 5) {
			callsPool.add(currentTime);
			resalt = true;
		}
		return resalt;
	}

}
