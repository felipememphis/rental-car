package com.rentalcar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rentalcar.entity.Client;
import com.rentalcar.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findByName(@RequestParam String name){
		Client client;
		try {
			client = service.findByName(name);
		} catch (Exception e) {
			return new ResponseEntity<>("cliente não encontrado", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@Valid @RequestBody Client client) {
		Client result = null;
		try {
			result = service.save(client);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("cliente não é maior de 18 anos!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Client client) {
		Client result = null;
		try {
			result = service.update(client, id);
		} catch (Exception e) {
			return new ResponseEntity<>("cliente não encontrado", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		try {
			service.remove(id);
		} catch (Exception e) {
			return new ResponseEntity<>("cliente não encontrado", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.noContent().build();
	}
	
}
