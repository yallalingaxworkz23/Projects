package com.xworkz.Pinxworkz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xworkz.Pinxworkz.dto.ProdectDTO;
import com.xworkz.Pinxworkz.dto.VendorDTO;
import com.xworkz.Pinxworkz.services.ProdectService;
import com.xworkz.Pinxworkz.services.VendorService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class ProdectController {

	@Autowired
	private ProdectService prodectService;

	@Autowired
	private VendorService vmanagementservice;

	public ProdectController() {
		log.info("invoking in the prodect controller..");
	}

	// by submit the prodect From call the action name as prodectRequired it send
	// the prodectDTO and save in the database..
	@PostMapping("/prodectRequired")
	public String onSaveProdect(@Valid ProdectDTO pdto, BindingResult binderrors, Model model,RedirectAttributes redirectAttributes) {
		log.info(""+binderrors.hasErrors());
		model.addAttribute("errors", pdto);
		log.info("mapping occurs for prodectRequored.." + pdto);

		if (binderrors.hasErrors()) {
			List<ObjectError> errors = binderrors.getAllErrors();
			errors.forEach(e -> System.err.println(e.getObjectName() + "message" + e.getDefaultMessage()));
			return "requiredProdect";
		} else {
			log.info("saveing for prodect required..");
			this.prodectService.onPrdectSave(pdto);
			redirectAttributes.addFlashAttribute(pdto);
			List<VendorDTO> listofVender = vmanagementservice.onFindAllVendor();
			model.addAttribute("vdto", listofVender);
			
			return "redirect:/adminprofile";
		}

	}
	
	@GetMapping("/adminprofile")
	private String adminprofile(Model model) {
		
		List<VendorDTO> listofVender = vmanagementservice.onFindAllVendor();
		model.addAttribute("vdto", listofVender);
		return "adminprofile";
	}

}
