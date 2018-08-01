package com.mynextcomp.DdosProtector.model;

public class ClientRequesCounter {

	private int counter = 0;

	public synchronized int incrementAndGet() {
		if (counter < 6) {
			counter++;
		}
		return counter;
	}

}
