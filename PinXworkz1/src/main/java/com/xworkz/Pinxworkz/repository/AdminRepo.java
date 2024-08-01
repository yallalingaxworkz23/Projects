package com.xworkz.Pinxworkz.repository;

import com.xworkz.Pinxworkz.entity.AdminEntity;
import com.xworkz.Pinxworkz.entity.VendorEntity;

public interface AdminRepo {
	
	public AdminEntity findByEmailidAndPassword(String emailid,String password);
	
	
	

}
