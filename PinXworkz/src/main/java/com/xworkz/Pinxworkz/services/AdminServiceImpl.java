package com.xworkz.Pinxworkz.services;

import java.time.LocalDate;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.Pinxworkz.constants.InVoiceConstants;
import com.xworkz.Pinxworkz.constants.VendorConstants;
import com.xworkz.Pinxworkz.dto.AdminDTO;
import com.xworkz.Pinxworkz.dto.InVoiceDTO;
import com.xworkz.Pinxworkz.dto.VendorDTO;
import com.xworkz.Pinxworkz.entity.AdminEntity;
import com.xworkz.Pinxworkz.entity.InVoiceEntity;
import com.xworkz.Pinxworkz.entity.VendorEntity;
import com.xworkz.Pinxworkz.repository.AdminRepo;
import com.xworkz.Pinxworkz.repository.InVoiceRepo;
import com.xworkz.Pinxworkz.repository.VendorRepo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private InVoiceService inVoiceService;
	
	@Autowired
	private InVoiceRepo inVoiceRepo;

	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private VendorRepo vendorRepo;

	@Override
	public AdminDTO findByEmailidPassword(String emailid, String password) {

		AdminEntity entity = adminRepo.findByEmailidAndPassword(emailid, password);
		log.info("to know what value is coming in service from repo.." + entity);
		if (entity != null) {
			AdminDTO adminDTO = new AdminDTO();
			BeanUtils.copyProperties(entity, adminDTO);

			return adminDTO;
		}
		return null;
	}

	@Override
	public boolean onApprovedOperaction(String emailid, String adminEmail) {
//calling find by emailid from reopo to adminservice 
		VendorEntity entity = vendorRepo.findByEmailid(emailid);

		entity.setUpdatedby(adminEmail);
		entity.setUpdateddate(LocalDate.now().toString());
		entity.setStatus(VendorConstants.APPROVED.toString());
//calling update method from repo to adminservice. to set status as approved and updatedby and updated date
       boolean isUpdated=vendorRepo.updatAndSaveOperaction(entity);
		return true;
	}

	
//hear I made that bring invoiceEntity by orderId where method is present in invpoiceservice
	//to bring invoice entity method is present in invoiceRepo reused it and set the status as Odered
	//and made update operaction method which is present in invoiceRepo
	@Override
	public boolean onActionOrderInAdminService(String orderId) {
		log.info("+++++++++++"+orderId);
		
		//on clikc order update and set statue as odered..
		InVoiceEntity inVoiceEntity= inVoiceRepo.onViewInvoiceInRepo(orderId); 
		log.info("==================="+inVoiceEntity);
		
		inVoiceEntity.setStatus(InVoiceConstants.Orderd.toString());
		boolean isUpdated= inVoiceRepo.onActionOrderUpdateOperaction(inVoiceEntity);
		return isUpdated;
	}

}
