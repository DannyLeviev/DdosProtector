package com.mynextcomp.DdosProtector.repository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mynextcomp.DdosProtector.model.ClientRequesCounter;

@Component
public class HttpClientCache {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientCache.class);
	private static final String NEW_COUNTER_WAS_CREATED_MSG = "CacheLoader creates a new instance of ClientRequesCounter for clientID = {}";

	private final static LoadingCache<String, ClientRequesCounter> cache = CacheBuilder.newBuilder()
			.expireAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<String, ClientRequesCounter>() {
				@Override
				public ClientRequesCounter load(String clientID) throws Exception {
					LOGGER.info(NEW_COUNTER_WAS_CREATED_MSG, clientID);
					return new ClientRequesCounter();
				}
			});

	public static ClientRequesCounter get(String clientID) throws ExecutionException {
		return cache.get(clientID);
	}
}
