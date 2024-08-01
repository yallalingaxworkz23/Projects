package com.xworkz.Pinxworkz.repository;

import java.util.List;

import com.xworkz.Pinxworkz.entity.VendorEntity;

public interface VendorRepo {
	
	public boolean save(VendorEntity vmentity);
	
	public VendorEntity findGstNo(String gstno);
	
	public VendorEntity findByContactNumber(String contactno);
	
	public VendorEntity findByEmailid(String emailid);
	
//	public Boolean updateEntity(VendorEntity entity);
	
//	public VendorEntity findById(VendorEntity id);
	
	public boolean updatAndSaveOperaction(VendorEntity ventity);
	
	public List<VendorEntity> onFindAllForAdmin();

	void expireOTP();
	
	
	
	

}
