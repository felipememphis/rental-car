package com.rentalcar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalcar.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
}
