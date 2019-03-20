package com.rentalcar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentalcar.entity.Client;
import com.rentalcar.repository.ClientRepository;
import com.rentalcar.userful.ValidDate;

@Service
@Transactional
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Client save(Client client) throws IllegalArgumentException {
		if (!ValidDate.isGreaterEighteenYears(client.getDateBirth())) {
			throw new IllegalArgumentException("cliente não é maior de 18 anos!");
		}
		return repository.save(client);
	}
	
	public Client update(Client client, Long id) throws Exception {
		return repository.findById(id).map(c -> {
			c.setAddress(client.getAddress());
			c.setCpf(client.getCpf());
			c.setDateBirth(c.getDateBirth());
			c.setDebit(client.getDebit());
			c.setEmail(client.getEmail());
			c.setPhone(client.getPhone());
			c.setTypePeople(client.getTypePeople());
			return repository.save(c);
		}).orElseThrow(() -> new Exception("Cliente não encontrado"));
	}

	public void remove(Long id) throws Exception {
		Client client = repository.findById(id)
				.orElseThrow(() -> new Exception("Cliente não encontrado"));
		repository.delete(client);
	}
	
	public Client findByName(String name) throws Exception {
		return repository.findByName(name)
				.orElseThrow(() -> new Exception("Cliente não encontrado"));
	}
}
