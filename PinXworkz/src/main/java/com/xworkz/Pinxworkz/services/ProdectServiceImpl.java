package com.xworkz.Pinxworkz.services;

import java.util.ArrayList;
import java.util.List;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.Pinxworkz.constants.InVoiceConstants;
import com.xworkz.Pinxworkz.dto.ProdectDTO;
import com.xworkz.Pinxworkz.entity.ProdectEntity;
import com.xworkz.Pinxworkz.repository.ProdectRepo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ProdectServiceImpl implements ProdectService{
	
	@Autowired
	private ProdectRepo prdectRepo;

	// required Prodects Save operaction  during save operaction set status as InvoicePending
	//and also set OrderId by using Random methodd..
	@Override
	public boolean onPrdectSave(ProdectDTO pdto) {
		
		ProdectEntity prodectEntity=new ProdectEntity();
		pdto.setStatus(InVoiceConstants.InvoicePending.toString());
		BeanUtils.copyProperties(pdto, prodectEntity);
		
		 String orderId= generateRandomPassword();
	     prodectEntity.setOrderId(orderId); 
	    // prodectEntity.setStatus(InVoiceConstants.PendingOrder.toString());
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

	//geting list of Requirementt-prodects and displaying the prodectDTO in profile page.
	//
	
	@Override
	public List<ProdectDTO> onListOfProdect() {
		
		List<ProdectEntity>  listofProdectEntitys=prdectRepo.findAllProdects();
		List<ProdectDTO> prdectDTOList=new ArrayList<ProdectDTO>();
		log.info("to know values are add are not."+prdectDTOList);
		listofProdectEntitys.forEach(prodects->{
			ProdectDTO pdto=new ProdectDTO();
			BeanUtils.copyProperties(prodects, pdto);
			prdectDTOList.add(pdto);
		});
		return prdectDTOList;
	}

	@Override
	public ProdectDTO onEditProdectDto(String orderId) {
		ProdectDTO prodectDto= findProductByOrderId(orderId);
		return prodectDto;
		//return findProductByOrderId(orderId);
	}

//	@Override
//	public ProdectDTO findPorductByOrderId(String orderId) {
//		return findProductByOrderId(orderId);
//	}

	@Override
	public ProdectDTO findProductByOrderId(String orderId) {
		ProdectEntity prodectEntity= prdectRepo.findProductOnOderId(orderId);
		ProdectDTO prodectDto=new ProdectDTO();
		BeanUtils.copyProperties(prodectEntity, prodectDto);
		return prodectDto;
	}

	@Override
	public boolean onUpdateProdect(ProdectDTO prodectDTO) {
		
		ProdectEntity prodectEntity=new ProdectEntity();
		BeanUtils.copyProperties(prodectDTO, prodectEntity);
		prodectEntity.setStatus(InVoiceConstants.InvoiceSent.toString());
		boolean isupdated= prdectRepo.onUpdateProdect(prodectEntity);
		
		return isupdated;
	}

	@Override
	public boolean onUpdateProdectByAdmin(ProdectDTO prodectDTO) {

		ProdectEntity prodectEntity=new ProdectEntity();
		prodectDTO.setStatus(InVoiceConstants.InvoicePending.toString());
		BeanUtils.copyProperties(prodectDTO, prodectEntity);
		boolean isUpdated= prdectRepo.onUpdateProdect(prodectEntity);
		
		return isUpdated;
	}

	@Override
	public List<ProdectDTO> onprodectListByProdectType(String prodectType) {
      List<ProdectEntity> listofProdectType=   prdectRepo.findAllByProdectType(prodectType);
      
      if(listofProdectType!=null) {
    	  
    	 List<ProdectDTO> ProdectTypeList=new ArrayList<ProdectDTO>();
    	 log.info("list of prodects on ProdectType  are..."+ProdectTypeList);
    	 listofProdectType.forEach(prodects->{
    		ProdectDTO prodectDTO=new ProdectDTO();
    		BeanUtils.copyProperties(prodects, prodectDTO);
    		ProdectTypeList.add(prodectDTO);
    	 });
    	 
    	 return ProdectTypeList;
    	 
      }
      
		return null;
	}

	

}
