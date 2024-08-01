package com.xworkz.Pinxworkz.repository;

import java.util.List;

import com.xworkz.Pinxworkz.entity.InVoiceEntity;

public interface InVoiceRepo {

	public boolean onSaveInVoiceRepo(InVoiceEntity inVoiceEntity); 
	
	public InVoiceEntity onViewInvoiceInRepo(String orderId);
	
	public List<InVoiceEntity> onViewInvoiceAdmin();
	
	public boolean onActionOrderUpdateOperaction(InVoiceEntity inVoiceEntity);
}
