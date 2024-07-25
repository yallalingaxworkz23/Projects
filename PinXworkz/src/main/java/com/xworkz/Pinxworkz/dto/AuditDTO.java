package com.xworkz.Pinxworkz.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public abstract class AuditDTO {

	private String createdby;
	private String createdate;
	private String updatedby;
	private String updateddate;
}
