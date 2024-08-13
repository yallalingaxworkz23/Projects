package com.xworkz.Pinxworkz.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xworkz.Pinxworkz.dto.InVoiceDTO;
import com.xworkz.Pinxworkz.dto.ProdectDTO;
import com.xworkz.Pinxworkz.dto.VendorDTO;
import com.xworkz.Pinxworkz.services.InVoiceService;
import com.xworkz.Pinxworkz.services.ProdectService;
import com.xworkz.Pinxworkz.services.VendorService;
import com.xworkz.Pinxworkz.services.VendorValidationService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController  //it returns data like string are int
@RequestMapping("/")
public class AjaxController {
	
	@Autowired
	private ProdectService prodectService;
	
	@Autowired
	private InVoiceService inVoicService;
	
	@Autowired
	private VendorValidationService venderValidationService;
	
	@Autowired
	private VendorService venderservice;
  
	public AjaxController() {
	log.info("running in the ajaxController.. ");
	}
	
	@GetMapping (value = "/uniquegstno/{gstno}")
	public String ongstno(@PathVariable String gstno) {
	     String gstnotext=venderValidationService.findGstNo(gstno);
	     if(gstnotext!=null) {
	    	 log.info("from ajax Controller gstno is already exist.."+gstno);
	    	 return gstnotext;
	     }
		
		return "";
	}
	
	@GetMapping(value = "/uniqueContact/{contactno}")
	public String oncontact(@PathVariable String contactno) {
		String contactText= venderValidationService.findContactNumber(contactno);
		if(contactText!=null) {
			log.info("from ajaxController contact is already preset.."+contactno);
			return contactText;
		}
		return "";
		
	}
	
	@GetMapping(value = "/uniqueEmail/{emailid}")
	public String onEmailid(@PathVariable String emailid) {
		log.info("to know which value is comeing to ajaxcontroller.. "+emailid);
		String emailtext= venderValidationService.findEmailid(emailid);
		if(emailtext!=null) {
			log.info("from ajax controller emailid is already present.."+emailid);
			return emailtext;
		}
		return "";
		
	}
	
	
	@GetMapping(value = "/generateOTP/{emailid}")
	public String generateOTP(@PathVariable String emailid) {
			String msg=venderservice.generateOTP(emailid);
			return msg;
	}
	
	@GetMapping(value="/getInvoice/{orderId}")
	public InVoiceDTO onViewInvoice(@PathVariable String orderId) {
		log.info("============= get invoice"+orderId);
		InVoiceDTO inVoiceDTOforView= inVoicService.onViewInVoiceInService(orderId);
		if(inVoiceDTOforView!=null) {
		log.info("============= get invoice"+inVoiceDTOforView);
		return inVoiceDTOforView ;
		}
          return null;
		
		 
	}
	
//	this methos make as a comment because it return string we need invoiceDto
//	@GetMapping(value="/forView")
//	public List<InVoiceDTO> onCkickViewInvoice() {
//		List<InVoiceDTO> listooInvoice= inVoicService.onViewAllInvoiceByAdmin();
//		log.info("to know orderids..in ajak."+listooInvoice);
//		return   listooInvoice;
//	}
//	
	
	
	//on click the edit in prodect-list by admin 
	//perform action like get Prodect data and display in model page..
	@GetMapping(value = "/forEditProdect/{orderId}")
	public ProdectDTO onEditProdect(@PathVariable String orderId) {
		ProdectDTO prodectDto= prodectService.findProductByOrderId(orderId);
		log.info("to know .."+prodectDto);
		return prodectDto;
	}
	
//	@RequestMapping(value = "/forSearchVender/{gstno}")
//	public String onforSearchVender(@PathVariable String gstno) {
//		String vdto= venderValidationService.findGstNo(gstno);
//		log.info("finding the venderDTO by emailid..+++++++++++++++++++++++++++++++++"+vdto);
//		return vdto;
//	}
	
}
