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

import com.xworkz.Pinxworkz.dto.ProdectDTO;
import com.xworkz.Pinxworkz.dto.VendorDTO;
import com.xworkz.Pinxworkz.services.ProdectService;
import com.xworkz.Pinxworkz.services.VendorService;

@Controller
@RequestMapping("/")
public class ProdectController {
	
	@Autowired
	private ProdectService prodectService;
	
	@Autowired
	private VendorService vmanagementservice;
	
	
	public ProdectController() {
	System.out.println("invoking in the prodect controller..");
	}

	@PostMapping("/prodectRequired")
	public String onSaveProdect(@Valid ProdectDTO pdto,BindingResult binderrors, Model model) {
		System.out.println(binderrors.hasErrors());
		model.addAttribute("errors",pdto);
		System.out.println("mapping occurs for prodectRequored.."+pdto);
		
		if(binderrors.hasErrors()) {
			 List<ObjectError> errors= binderrors.getAllErrors();
			 errors.forEach(e->System.err.println(e.getObjectName()+"message"+ e.getDefaultMessage())); 
			 return "requiredProdect";
		}else {
			System.out.println("saveing for prodect required..");
			this.prodectService.onPrdectSave(pdto);
			List<VendorDTO> listofVender= vmanagementservice.onFindAllVendor();
			model.addAttribute("vdto", listofVender);
			return "adminprofile";
		}
		
		
	}
	
}
