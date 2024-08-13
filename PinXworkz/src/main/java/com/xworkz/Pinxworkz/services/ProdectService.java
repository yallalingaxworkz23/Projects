package com.xworkz.Pinxworkz.services;

import java.util.List;

import com.xworkz.Pinxworkz.dto.ProdectDTO;

public interface ProdectService {
	
	public boolean onPrdectSave(ProdectDTO pdto);
	
	public List<ProdectDTO> onListOfProdect();
	
	public ProdectDTO onEditProdectDto(String orderId);

//	public ProdectDTO findPorductByOrderId(String orderId);

	public ProdectDTO findProductByOrderId(String orderId);
	
	public boolean onUpdateProdect(ProdectDTO prodectDTO);

	public boolean onUpdateProdectByAdmin(ProdectDTO prodectDTO);
	
	public List<ProdectDTO> onprodectListByProdectType(String prodectType);
	
}
