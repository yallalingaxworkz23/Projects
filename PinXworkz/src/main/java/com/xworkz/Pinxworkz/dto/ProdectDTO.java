package com.xworkz.Pinxworkz.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class ProdectDTO {
	
	
	private int id;
	
	@NotNull
	@Size(min = 3,max = 20,message = "must be 3 to 20")
	private String prodectType;
	
	@NotNull
	@Size(min = 3,max = 20,message = "must be 3 to 20")
	private String prodect;
	
	@NotNull
	@Size(min = 3,max = 25,message = "must be 3 to 25")
	private String location;
	
	@NotNull
	private String requireddate;
	
	@NotNull
	private int totalRequired;
	
	
	private String orderId;
	
	private String status;

}
