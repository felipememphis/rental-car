package com.rentalcar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalcar.entity.Client;
import com.rentalcar.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody Client client) {
		Client result = null;
		try {
			result = service.save(client);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("cliente não é maior de 18 anos!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
}
