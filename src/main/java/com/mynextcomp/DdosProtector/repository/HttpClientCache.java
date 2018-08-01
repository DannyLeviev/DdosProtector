package com.mynextcomp.DdosProtector.repository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mynextcomp.DdosProtector.model.ClientRequesCounter;

@Component
public class HttpClientCache {

	private final static LoadingCache<String, ClientRequesCounter> cache = CacheBuilder.newBuilder()
			.expireAfterWrite(5, TimeUnit.SECONDS).build(new CacheLoader<String, ClientRequesCounter>() {
				@Override
				public ClientRequesCounter load(String clientID) throws Exception {
					return new ClientRequesCounter();
				}
			});

	public static ClientRequesCounter get(String clientID) throws ExecutionException {
		ClientRequesCounter result = cache.get(clientID);
		return result;
	}
}
