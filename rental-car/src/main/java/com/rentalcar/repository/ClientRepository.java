package com.rentalcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalcar.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
