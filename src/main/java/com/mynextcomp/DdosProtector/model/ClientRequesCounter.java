package com.mynextcomp.DdosProtector.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientRequesCounter {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientRequesCounter.class);
	private int counter = 0;

	public synchronized int incrementAndGet() {
		if (counter < 6) {
			counter++;
			LOGGER.info("ClientRequesCounter.incrementAndGet() returned - {}", counter);

		} else {
			LOGGER.warn("ClientRequesCounter reached its maximum value = {}", counter);
		}
		return counter;
	}

}
