package com.myntra.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
	private int addId;
	@NotNull
	private String name;
	private String emailId;
	private String addressLine1;
	@NotNull
	private Long pincode;
	private String state;
	private String locality;
	private String city;
	private String addressType;

}
