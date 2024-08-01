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
		System.out.println("to know what value is coming in service from repo.." + entity);
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
//calling update method from repo to adminservice.
       boolean isUpdated=vendorRepo.updatAndSaveOperaction(entity);
		return true;
	}

	@Override
	public boolean onActionOrderInAdminService(String orderId) {
		//on clikc order update and set statue as odered..
		InVoiceEntity inVoiceEntity= inVoiceRepo.onViewInvoiceInRepo(orderId); 
		inVoiceEntity.setStatus(InVoiceConstants.Orderd.toString());
		boolean isUpdated= inVoiceRepo.onActionOrderUpdateOperaction(inVoiceEntity);
		return true;
	}

}
