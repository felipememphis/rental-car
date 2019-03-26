package com.rentalcar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Client extends People{
	
	@Past
	@Temporal(TemporalType.DATE)
	private Date dateBirth;
	
	@Column(nullable = true)
	private String debit;
}
