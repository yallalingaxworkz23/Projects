package com.xworkz.Pinxworkz.services;

import java.util.ArrayList;
import java.util.List;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.Pinxworkz.dto.ProdectDTO;
import com.xworkz.Pinxworkz.entity.ProdectEntity;
import com.xworkz.Pinxworkz.repository.ProdectRepo;

@Service
public class ProdectServiceImpl implements ProdectService{
	
	@Autowired
	private ProdectRepo prdectRepo;

	@Override
	public boolean onPrdectSave(ProdectDTO pdto) {
		
		ProdectEntity prodectEntity=new ProdectEntity();
		BeanUtils.copyProperties(pdto, prodectEntity);
		
		 String orderId= generateRandomPassword();
	     prodectEntity.setOrderId(orderId); 
        this.prdectRepo.onProdectSaveRepo(prodectEntity);
         
		return true;
	}
	
	//seting orderId for prodect
	public String generateRandomPassword() {

		PasswordGenerator passGen = new PasswordGenerator();

		CharacterRule lcr = new CharacterRule(EnglishCharacterData.UpperCase);
		lcr.setNumberOfCharacters(2);

		CharacterRule dr = new CharacterRule(EnglishCharacterData.Digit);
		dr.setNumberOfCharacters(2);

		String randomPassword = passGen.generatePassword(6, lcr, dr);
		return randomPassword;

	}

	
	
	@Override
	public List<ProdectDTO> onListOfProdect() {
		
		List<ProdectEntity>  listofProdectEntitys=prdectRepo.findAllProdects();
		List<ProdectDTO> prdectDTOList=new ArrayList<ProdectDTO>();
		System.out.println("to know values are add are not."+prdectDTOList);
		listofProdectEntitys.forEach(prodects->{
			ProdectDTO pdto=new ProdectDTO();
			BeanUtils.copyProperties(prodects, pdto);
			prdectDTOList.add(pdto);
		});
		return prdectDTOList;
	}

	@Override
	public ProdectDTO onEditProdectDto(String orderId) {
		ProdectEntity prodectEntity= prdectRepo.findProductOnOderId(orderId);
		ProdectDTO prodectDto=new ProdectDTO();
		BeanUtils.copyProperties(prodectEntity, prodectDto);
		return prodectDto;
	}

	@Override
	public boolean onUpdateProdect(ProdectDTO prodectDTO) {
		
		ProdectEntity prodectEntity=new ProdectEntity();
		BeanUtils.copyProperties(prodectDTO, prodectEntity);
		boolean isupdated= prdectRepo.onUpdateProdect(prodectEntity);
		
		return isupdated;
	}

	

}
