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

@Entity
@Data
@Getter
@Setter
@Table(name="invoice")
@NamedQuery(name="findByOrderIdForInvoice",query = "select inv from InVoiceEntity inv where inv.orderId=:invgenrated")
@NamedQuery(name="vewAllInVoice",query = "select alv from InVoiceEntity alv")
public class InVoiceEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="in_id")
	private int id; 
	
	@Column(name="order_id")
	private String orderId;
	
	@Column(name="in_price")
	private double price;
	
	@Column(name="in_delivarycharge")
	private double delivarycharge;
	
	@Column(name="in_discriptioin")
	private String discription;
	
	@Column(name="in_status")
	private String status;

}
