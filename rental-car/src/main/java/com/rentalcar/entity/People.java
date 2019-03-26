package com.rentalcar.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.rentalcar.enums.TypePeople;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class People{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	@CPF
	@NotNull
	private String cpf;
	
	@NotNull
	private String name;
	
	@NotNull
	private String phone;
	
	@NotNull
	@Email
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pk_address")
	private Address address;
	
	@Enumerated(EnumType.STRING)
	private TypePeople typePeople;
}
