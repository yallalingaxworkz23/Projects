package com.xworkz.Pinxworkz.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class InVoiceDTO {
	
	private int id;
	
	@NotNull
	private String orderId;
	
	@NotNull
	private double price;
	
	@NotNull
	private double delivarycharge;
	
	@NotNull
	private String discription;
	
	
	private String status;

}
