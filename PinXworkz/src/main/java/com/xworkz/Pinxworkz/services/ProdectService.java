package com.xworkz.Pinxworkz.services;

import java.util.List;

import com.xworkz.Pinxworkz.dto.ProdectDTO;

public interface ProdectService {
	
	public boolean onPrdectSave(ProdectDTO pdto);
	
	public List<ProdectDTO> onListOfProdect();
	
	public ProdectDTO onEditProdectDto(String orderId);
	
	public boolean onUpdateProdect(ProdectDTO prodectDTO);

}
