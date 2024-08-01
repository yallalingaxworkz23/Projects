package com.xworkz.Pinxworkz.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.xworkz.Pinxworkz.constants.InVoiceConstants;
import com.xworkz.Pinxworkz.dto.InVoiceDTO;
import com.xworkz.Pinxworkz.dto.ProdectDTO;
import com.xworkz.Pinxworkz.entity.InVoiceEntity;
import com.xworkz.Pinxworkz.repository.InVoiceRepo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class InVoiceServiceImpl implements InVoiceService {
//	@Autowired
//	private ProdectService predectService;

	@Autowired
	private ProdectService prodectService;

	@Autowired
	private InVoiceRepo inVoiceRepo;

	@Override
	public boolean onSaveInVoice(InVoiceDTO inVoicedto) {

		// prodectService.onUpdateProdect(InVoiceConstants.Orderd.toString());

		// during invoice save operaction geting prodect dto for perticular OrderId
		// where get orderId by InvoiceDTO orderId
		// to find prodectDTO By orderId method is present in ProdectService methos is
		// findProductByOrderId()
		ProdectDTO prodectDTO = prodectService.findProductByOrderId(inVoicedto.getOrderId());
		// prodectDTO.setStatus(InVoiceConstants.Invoiced.toString());
//after find the proderctDto of perticular OrderId do UpdateProdectDTO operaction.. 
		prodectService.onUpdateProdect(prodectDTO);

		InVoiceEntity inVoiceEntity = new InVoiceEntity();
		inVoicedto.setStatus(InVoiceConstants.PendingOrder.toString());
		BeanUtils.copyProperties(inVoicedto, inVoiceEntity);
		// prodectService.findProductByOrderId(inVoiceEntity.getOrderId());

		// inVoicedto.setOrderId(inVoicedto.getOrderId());
		boolean Return = this.inVoiceRepo.onSaveInVoiceRepo(inVoiceEntity);
		if (Return == true) {
			return true;
		}
		return false;
	}

	@Override
	public InVoiceDTO onViewInVoiceInService(String orderId) {

		InVoiceEntity inVoiceData = inVoiceRepo.onViewInvoiceInRepo(orderId);
		if (inVoiceData != null) {
			InVoiceDTO inVoiceDTO = new InVoiceDTO();
			BeanUtils.copyProperties(inVoiceData, inVoiceDTO);

			return inVoiceDTO;
		}
		return null;
	}

	@Override
	public List<InVoiceDTO> onViewAllInvoiceByAdmin() {

		List<InVoiceEntity> allInvoiceEntity = inVoiceRepo.onViewInvoiceAdmin();
		log.info("from repo to service to know invoice list..on orderId" + allInvoiceEntity);
		List<InVoiceDTO> allInvoiceDtoList = new ArrayList<InVoiceDTO>();

		allInvoiceEntity.forEach(listofInvoices -> {
			InVoiceDTO inVoiceDTO = new InVoiceDTO();
			BeanUtils.copyProperties(listofInvoices, inVoiceDTO);
			allInvoiceDtoList.add(inVoiceDTO);
		});
		log.info("to know invoice list..on orderId" + allInvoiceDtoList);
		return allInvoiceDtoList;
	}

}
