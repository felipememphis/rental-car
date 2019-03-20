package com.rentalcar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rentalcar.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query("select c from Client c where c.name like %?1%")
	Optional<Client> findByName(String name);

}
