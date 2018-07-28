package com.mynextcomp.DdosProtector.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mynextcomp.DdosProtector.repositories.HttpClientDAO;

@Service
public class DdosProtectorService {

	private HttpClientDAO clientsRepo;

	@Autowired
	public DdosProtectorService(HttpClientDAO clientsRepo) {
		this.clientsRepo = clientsRepo;
	}

	public boolean validateClientRequest(String clientID) {
		boolean result = false;
		if(clientsRepo.regiteredClient(clientID)) {
			result = clientsRepo.tryToServe(clientID);
		}
		else {
			clientsRepo.register(clientID);
			result = true;
		}
		return result;
	}

}
