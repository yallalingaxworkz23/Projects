package com.xworkz.Pinxworkz.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.xworkz.Pinxworkz.constants.InVoiceConstants;
import com.xworkz.Pinxworkz.dto.InVoiceDTO;
import com.xworkz.Pinxworkz.entity.InVoiceEntity;
import com.xworkz.Pinxworkz.repository.InVoiceRepo;

@Service
public class InVoiceServiceImpl  implements InVoiceService{
//	@Autowired
//	private ProdectService predectService;
	
	
	
	@Autowired 
	private InVoiceRepo inVoiceRepo;

	@Override
	public boolean onSaveInVoice(InVoiceDTO inVoicedto) {
		
		InVoiceEntity inVoiceEntity=new InVoiceEntity();
        inVoicedto.setStatus(InVoiceConstants.PendingOrder.toString());
		BeanUtils.copyProperties(inVoicedto, inVoiceEntity);
		//inVoicedto.setOrderId(inVoicedto.getOrderId());
	boolean Return=	this.inVoiceRepo.onSaveInVoiceRepo(inVoiceEntity);
		if(Return==true) {
			return true;	
		}
		return false;
	}

	@Override
	public InVoiceDTO onViewInVoiceInService(String orderId) {
		
		InVoiceEntity inVoiceData=  inVoiceRepo.onViewInvoiceInRepo(orderId);
		if(inVoiceData!=null) {
		InVoiceDTO inVoiceDTO=new InVoiceDTO();
		BeanUtils.copyProperties(inVoiceData, inVoiceDTO);

		return inVoiceDTO;
		}
		return null;
	}

	@Override
	public List<InVoiceDTO> onViewAllInvoiceByAdmin() {
		
        List<InVoiceEntity> allInvoiceEntity= inVoiceRepo.onViewInvoiceAdmin();
        System.out.println("from repo to service to know invoice list..on orderId"+allInvoiceEntity);
        List<InVoiceDTO> allInvoiceDtoList=new ArrayList<InVoiceDTO>();
		
		allInvoiceEntity.forEach(listofInvoices->{
			InVoiceDTO inVoiceDTO=new InVoiceDTO();
		    BeanUtils.copyProperties(listofInvoices, inVoiceDTO);
		    allInvoiceDtoList.add(inVoiceDTO);
		});
		System.out.println("to know invoice list..on orderId"+allInvoiceDtoList);
		return allInvoiceDtoList;
	}

}
