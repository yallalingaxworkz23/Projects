package com.xworkz.Pinxworkz.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.xworkz.Pinxworkz.dto.AuditDTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Table(name="vmanagemantrigester")
@Entity
@NamedQuery(name = "expireOTP", query = "update VendorEntity entity set entity.isOtpExpired=true where entity.otpSentTime<:currentTime")
@NamedQuery(name="findAllForAdmin",query = "Select fa from VendorEntity fa")
@NamedQuery(name="findById",query = "Select pr from VendorEntity pr where pr.id=:idn")
@NamedQuery(name = "findByContactNumber",query = "Select cn from VendorEntity cn where cn.contactno=:yk")
@NamedQuery(name = "findByGstNo",query = "select gs from VendorEntity gs where gs.gstno=:vgs")
@NamedQuery(name = "findByEmailid",query = "select em from VendorEntity em where em.emailid=:vem")

public class VendorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="v_id")
	private int id;

	@Column(name = "v_name")
	private String name;
	
	@Column(name = "v_location")
	private String location;
	
	@Column(name = "v_gstno")
	private String gstno;
	
	@Column(name = "v_companyStartDate")
	private String companyStartDate;
	
	@Column(name = "v_ownername")
	private String ownername;
	
	@Column(name = "v_servicetype")
	private String servicetype;
	
	@Column(name = "v_contactno",columnDefinition = "bigint")
	private String contactno;
	
	@Column(name = "v_alternativeno")
	private Long alternativeno;
	
	@Column(name = "v_emailid" )
	private String emailid;
	
	@Column(name = "v_website")
	private String website;
	
	@Column(name = "v_createdby")
	private String createdby;
	
	@Column(name = "v_createdate")
	private String createdate;
	
	@Column(name = "v_updatedby")
	private String updatedby;
	
	@Column(name = "v_updateddate")
	private String updateddate;
	
	@Column(name="v_otp")
	private String otp;
	
	@Column(name="v_status")
	private String status;
	
	@Column(name="v_login_count")
    private int loginCount;
	
	@Column(name="v_accountLockTime")
	private LocalDateTime accountLockTime;
	
	@Column(name="v_isOtpExpired")
	private boolean isOtpExpired;
	
	@Column(name="v_otpSentTime")
	private LocalDateTime otpSentTime;
	
	@Column(name="v_profile_picName")
	private String picName;

}
