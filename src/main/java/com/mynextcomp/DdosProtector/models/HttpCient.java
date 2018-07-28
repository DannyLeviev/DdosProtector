package com.mynextcomp.DdosProtector.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class HttpCient {

	private String id;
	private long firstRequestTime;
	private int reqCounter;

	@Builder
	public HttpCient(String id, long firstRequestTime, int reqCounter) {
		super();
		this.id = id;
		this.firstRequestTime = firstRequestTime;
		this.reqCounter = reqCounter;
	}

}
