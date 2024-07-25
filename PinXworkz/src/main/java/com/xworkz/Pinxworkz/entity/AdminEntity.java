package com.xworkz.Pinxworkz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@Getter
@Setter
@Entity
@Table(name="admin_details")
@NamedQuery(name = "adminLogin",query = "select ad from AdminEntity ad where ad.emailid=:email and ad.password=:password ")
public class AdminEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="a_id")
	private int id;
	
	@Column(name="a_emailid")
	private String emailid;
	
	@Column(name="a_password")
	private String password;

}
