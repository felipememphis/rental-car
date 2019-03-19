package com.rentalcar;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.rentalcar.entity.Address;
import com.rentalcar.entity.Client;
import com.rentalcar.service.ClientService;

@SpringBootApplication
public class RentalCarApplication {

	@Autowired
	private ClientService service;
	
	public static void main(String[] args) {
		SpringApplication.run(RentalCarApplication.class, args);
	}
	
	
	@Bean
	public void save() {
		Address address = new Address();
		address.setComplement(null);
		address.setNeighborhood("santo antonio");
		address.setNumber(2400);
		address.setStreet("av carangola");
		Client client = new Client();
		client.setAddress(address);
		client.setCpf("16288371874");
		Calendar calendar = Calendar.getInstance();
		client.setDateBirth(new Date(1990, 10, 10));
		client.setEmail("teste@gmail.com");
		client.setPhone("983152733");
		service.save(client);
		
	}

}
