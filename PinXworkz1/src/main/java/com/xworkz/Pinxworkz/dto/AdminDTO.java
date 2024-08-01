package com.xworkz.Pinxworkz.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AdminDTO {
	
	private int id;
	
	@NotNull
	@Email
	private String emailid;
	
	@NotNull
	@Size(min = 5,max = 25,message = "must be 5 to 25")
	private String password;

}
