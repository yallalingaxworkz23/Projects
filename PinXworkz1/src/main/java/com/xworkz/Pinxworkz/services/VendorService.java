package com.xworkz.Pinxworkz.services;

import java.util.List;

import com.xworkz.Pinxworkz.dto.VendorDTO;
import com.xworkz.Pinxworkz.entity.VendorEntity;

public interface VendorService {
	
	public boolean saveOperation(VendorDTO vdto);
	
	public boolean sendmail(String mail,String subject,String body);
	
	public String generateOTP(String emailid);
	
	public String otpValidation(String emailid,String otp);
	
	public VendorDTO findByEmailID(String emailid);
	
    void setLoginCountZero();
	
	public boolean afterUpdateSaveOperaction(VendorDTO vdto);
	
	public List<VendorDTO> onFindAllVendor();
	
	

}
