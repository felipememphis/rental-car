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
import com.rentalcar.userful.ValidDate;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@PostMapping
	public ResponseEntity<?> save(@Valid @RequestBody Client client) {
		if (!ValidDate.isGreaterEighteenYears(client.getDateBirth())) {
			return new ResponseEntity<>("cliente não é maior de 18 anos!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(service.save(client), HttpStatus.CREATED);
	}
}
