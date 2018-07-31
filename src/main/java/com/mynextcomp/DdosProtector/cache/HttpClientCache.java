package com.mynextcomp.DdosProtector.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Component
public class HttpClientCache {

	private static final Cache<String, Integer> cache = CacheBuilder.newBuilder().expireAfterWrite(50, TimeUnit.SECONDS).build();

	
	public static Integer get(final String clientID) throws ExecutionException {
		try {
			return cache.get(clientID, new Callable<Integer>() {
				@Override
				public Integer call() {
					return calculateNextValue(clientID);
				}
			});
		} catch (ExecutionException e) {
			throw e;
		}
	}

//	public static final Cache<String, Integer> cache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS)
//			.build(new CacheLoader<String, Integer>() {
//				public Integer load(String clientID) throws ExecutionException {
//					return calculateNextValue(clientID);
//				}
//			});
//
	private static Integer calculateNextValue(String clientID) {
		Integer cachedInfo = cache.getIfPresent(clientID);
		//If not existed then this is the first call, otherwise increase by 1:
		cachedInfo = cachedInfo == null ? 1 : cachedInfo + 1;
		//dont let the value to be more then 5:
		return cachedInfo > 5 ? 5 : cachedInfo;
	}

}
