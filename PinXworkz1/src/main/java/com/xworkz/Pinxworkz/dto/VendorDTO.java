package com.xworkz.Pinxworkz.dto;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class VendorDTO extends AuditDTO {
	
	private int id;
	
	@NotNull
	@Size(min = 4,max = 25,message = "name =>4 & <=50")
	private String name;
	
	@NotNull
	@Size(min = 4,max = 25,message = "location =>4 & <=50")
	private String location;
	
	@NotNull
	@Size(min=4,max = 25,message = "gstno =>4 & <=50")
	private String gstno;
	
	@NotNull
	private String companyStartDate;
	
	@NotNull
	@Size(min = 4,max = 25,message = "ownername =>4 &<=50")
	private String ownername;
	
	@NotNull
    @Size(min = 4,max = 25,message = "servicetype =>4 &<=50")
	private String servicetype;
	
	@NotNull
	private String contactno;
	
	@NotNull
	private Long alternativeno;
	
	@NotNull
	@Email
	private String emailid;

	@NotNull
	@Size(min = 4,max = 45,message = "website =>4 & <=100")
	private String website;
	
	private String otp;
	
	private String status;
	
	private int loginCount;
	
	private LocalDateTime accountLockTime;
	
}
