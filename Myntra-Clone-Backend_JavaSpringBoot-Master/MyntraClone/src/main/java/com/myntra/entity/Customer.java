package com.myntra.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.data.annotation.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String email;
	private String name;
	@Transient
	private String password;
	private Long phoneNumber;
	private String gender;
	@OneToMany(mappedBy = "addCustomer" ,cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Address> address;
	@OneToOne(mappedBy = "authCustomer",cascade = CascadeType.ALL,orphanRemoval = true)
	private CustomerAuth customerAuth;
	

}
