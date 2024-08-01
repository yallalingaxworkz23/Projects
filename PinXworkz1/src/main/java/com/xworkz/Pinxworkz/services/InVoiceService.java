package com.xworkz.Pinxworkz.services;

import java.util.List;

import com.xworkz.Pinxworkz.dto.InVoiceDTO;

public interface InVoiceService {
	
	
	public boolean  onSaveInVoice(InVoiceDTO inVoicedto);
	
	public InVoiceDTO  onViewInVoiceInService(String orderId);
	
	public List<InVoiceDTO> onViewAllInvoiceByAdmin();

}
