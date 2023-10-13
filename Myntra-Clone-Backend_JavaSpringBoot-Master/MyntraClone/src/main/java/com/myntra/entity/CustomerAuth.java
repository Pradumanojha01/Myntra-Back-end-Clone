package com.myntra.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customer_authentication")
public class CustomerAuth {

	@Id
	private String email;
	private String password;
	@OneToOne
	@JoinColumn(name = "user_id")
	private Customer authCustomer;
	
}