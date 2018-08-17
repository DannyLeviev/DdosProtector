package com.mynextcomp.DdosProtector.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientCallsPool {

	private final List<Long> callsPool = new ArrayList<>();
	private final static Logger LOGGER = LoggerFactory.getLogger(ClientCallsPool.class);

	public synchronized boolean canServe() {
		boolean resalt = false;
		Long currentTime = java.lang.System.currentTimeMillis();

		LOGGER.info(String.valueOf(callsPool.size()));
		// Clean older than 5 sec calls
		Iterator<Long> itr = callsPool.iterator();
		while (itr.hasNext()) {
			if (currentTime - itr.next() > 5000) {
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
