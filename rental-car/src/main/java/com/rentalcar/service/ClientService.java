package com.rentalcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentalcar.entity.Client;
import com.rentalcar.repository.ClientRepository;
import com.rentalcar.userful.ValidDate;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Transactional
	public Client save(Client client) throws IllegalArgumentException {
		if (!ValidDate.isGreaterEighteenYears(client.getDateBirth())) {
			throw new IllegalArgumentException("cliente não é maior de 18 anos!");
		}
		return repository.save(client);
	}
}
