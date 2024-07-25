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

@Entity
@Data
@Table(name="prodect_details")
@NamedQuery(name = "allProdects",query = "select pa from ProdectEntity pa")
@NamedQuery(name="findProdectByOrderId",query = "select prd from ProdectEntity prd where prd.orderId=:orderid")
@Getter
@Setter
@ToString
public class ProdectEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="p_id")
	private int id;
	
	@Column(name="p_prodectType")
	private String prodectType;
	
	@Column(name="p_prodect")
	private String prodect;
	
	@Column(name="p_location")
	private String location;
	
	@Column(name="p_requireddate")
	private String requireddate;
	
	@Column(name="p_totalRequired")
	private Integer totalRequired;
	
	@Column(name="p_orderId")
	private String orderId;


}
