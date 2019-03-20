package com.rentalcar.controller;

import java.util.List;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/client")
@Api(value="Api rest for client")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Busca o cliente pelo seu nome", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code=200, message="Sucesso ao buscar cliente por nome."),
			@ApiResponse(code=404, message="Cliente não encontrado!"),
	})
	public ResponseEntity<?> findByName(@ApiParam(value="Nome do cliente a ser pesquisado", required=true)@RequestParam String name){
		Client client;
		try {
			client = service.findByName(name);
		} catch (Exception e) {
			return new ResponseEntity<>("cliente não encontrado", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Salva o cliente", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code=201, message="Sucesso ao salvar o cliente."),
			@ApiResponse(code=400, message="Cliente não é maior de 18 anos!"),
	})
	public ResponseEntity<?> save(@ApiParam(value="Dados do cliente a ser salvo", required=true)@Valid @RequestBody Client client) {
		Client result = null;
		try {
			result = service.save(client);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>("cliente não é maior de 18 anos!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,  consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="Atualiza o cliente", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code=200, message="Sucesso ao salvar o cliente."),
			@ApiResponse(code=404, message="Cliente não encontrado!"),
	})
	public ResponseEntity<?> update(@ApiParam(value="Dados do cliente a ser atualizado", required=true)@PathVariable Long id, @Valid @RequestBody Client client) {
		Client result = null;
		try {
			result = service.update(client, id);
		} catch (Exception e) {
			return new ResponseEntity<>("cliente não encontrado", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value="Remove o cliente", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code=204, message="Sucesso ao excluir o cliente."),
			@ApiResponse(code=404, message="Cliente não encontrado!"),
	})
	public ResponseEntity<?> remove(@ApiParam(value="Id do cliente a ser removido", required=true)@PathVariable Long id) {
		try {
			service.remove(id);
		} catch (Exception e) {
			return new ResponseEntity<>("cliente não encontrado", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.noContent().build();
	}
	
}
