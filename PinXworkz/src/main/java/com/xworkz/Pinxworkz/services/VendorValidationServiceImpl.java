package com.xworkz.Pinxworkz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.Pinxworkz.entity.VendorEntity;
import com.xworkz.Pinxworkz.repository.VendorRepo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class VendorValidationServiceImpl implements VendorValidationService{

	@Autowired
	private VendorRepo vmanagementrepo;


	@Override
	public String findContactNumber(String contactno) {
		VendorEntity singleContactNoResult = vmanagementrepo.findByContactNumber(contactno);
		if (singleContactNoResult != null) {
			if (singleContactNoResult.getContactno().equals(contactno)) {
				return "number is already exist";
			}
		}
		log.info("number is not present save it.." + contactno);
		return null;
	}

	@Override
	public String findGstNo(String gstno) {
		VendorEntity singleGstNoResult = vmanagementrepo.findGstNo(gstno);
		if (singleGstNoResult != null) {
			if (singleGstNoResult.getGstno().equals(gstno)) {
				return "gstno is alrady exist";
			}
		}
		log.info("gstno is not present save it.." + gstno);
		return null;
	}

	@Override
	public String findEmailid(String emailid) {
//		log.info("to know what value is comming in service.."+emailid);
		VendorEntity singleEmailResult = vmanagementrepo.findByEmailid(emailid);
	//	log.info("to know what value is comming from repo to service.."+singleEmailResult);
		
		if (singleEmailResult != null) {
			if (singleEmailResult.getEmailid().equals(emailid)) {
				return "email is already exist";
			}
		}
		log.info("emailis is not present save it.." + emailid);
		return null;
	}

}