package com.xworkz.Pinxworkz.services;

import com.xworkz.Pinxworkz.dto.AdminDTO;
import com.xworkz.Pinxworkz.dto.VendorDTO;

public interface AdminService {
	
	public AdminDTO findByEmailidPassword(String emailid,String password);
	
	public boolean onApprovedOperaction(String emailid,String adminEmail);
	
	public boolean onActionOrderInAdminService(String orderId);

}
