package com.xworkz.Pinxworkz.repository;

import java.util.List;

import com.xworkz.Pinxworkz.dto.ProdectDTO;
import com.xworkz.Pinxworkz.entity.ProdectEntity;

public interface ProdectRepo {
	
	public boolean onProdectSaveRepo(ProdectEntity prodectEntity);
	
	public List<ProdectEntity> findAllProdects();
	
	public ProdectEntity findProductOnOderId(String orderId);
	
	public boolean onUpdateProdect(ProdectEntity prodectEntity);

}
