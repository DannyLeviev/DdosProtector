package com.mynextcomp.DdosProtector.repositories;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mynextcomp.DdosProtector.models.HttpCient;

@Repository
public class HttpClientDAO {

	private ConcurrentHashMap<String, HttpCient> clientsMap;

	@Autowired
	public HttpClientDAO() {
		this.clientsMap = new ConcurrentHashMap<>();
	}

	public void register(String clientID) {
		HttpCient newClnt = HttpCient.builder().id(clientID).firstRequestTime(System.currentTimeMillis()).reqCounter(1)
				.build();
		clientsMap.put(clientID, newClnt);
	}

	public boolean regiteredClient(String clientID) {
		return clientsMap.containsKey(clientID);
	}

	public boolean tryToServe(String clientID) {
		boolean result = false;
		HttpCient client = clientsMap.get(clientID);
		if (System.currentTimeMillis() - client.getFirstRequestTime() < 5000) {
			if (client.getReqCounter() < 5) {
				client.setReqCounter(client.getReqCounter() + 1);
				result = true;
			}
		} else {
			client.setFirstRequestTime(System.currentTimeMillis());
			client.setReqCounter(1);
			result = true;
		}
		return result;
	}

}
