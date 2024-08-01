package com.xworkz.Pinxworkz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.Pinxworkz.dto.InVoiceDTO;
import com.xworkz.Pinxworkz.dto.ProdectDTO;
import com.xworkz.Pinxworkz.services.InVoiceService;
import com.xworkz.Pinxworkz.services.ProdectService;
import com.xworkz.Pinxworkz.services.VendorService;

@Controller
@RequestMapping("/")
public class InVoiceController {
	@Autowired
	private VendorService vmanagementService;
	
	@Autowired
	private ProdectService prodectService;
	
	@Autowired
	private InVoiceService inVoiceService;

	public InVoiceController() {
	System.out.println("running in the onvoice controller..");
	}
	
	@PostMapping("/generateInvoice")
	public String onInvoice(@Valid InVoiceDTO inVoicedto, BindingResult erroeinInvoice,Model model ) {
		
		System.out.println(erroeinInvoice.hasErrors());
		System.out.println("Invoice Dto are .."+inVoicedto);
		if(erroeinInvoice.hasErrors()) {
			List<ObjectError> errorsList= erroeinInvoice.getAllErrors();
			errorsList.forEach(y->System.err.println(y.getObjectName()+"message"+y.getDefaultMessage()));
			return "profilepage";
		}else {
			
		 boolean anwer=	this.inVoiceService.onSaveInVoice(inVoicedto);
		 if(anwer==true) {
			
			 List<ProdectDTO> listOfProducts= prodectService.onListOfProdect();
			  model.addAttribute("pdtos", listOfProducts);
			 
			return "profilepage";
		}
		 return "profilepage";
		 }
		
	}
}
