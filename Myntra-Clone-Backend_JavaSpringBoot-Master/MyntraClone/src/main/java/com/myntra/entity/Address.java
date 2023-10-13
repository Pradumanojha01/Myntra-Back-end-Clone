package com.myntra.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addId;
	private String userIdEmail;
	private String name;
	private String addressEmail;
	private String addressLine1;
	private String pincode;
	private String state;
	private String locality;
	private String city;
	private String addressType;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer addCustomer;

}