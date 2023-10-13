
package com.myntra.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

	private int userId;
	@NotBlank(message = "Email can not be blank")
	private String email;
	@NotNull
	private String name;
	@NotNull(message = "Password can not be null")
	private String password;
	private Long phoneNumber;
	private String gender;

}