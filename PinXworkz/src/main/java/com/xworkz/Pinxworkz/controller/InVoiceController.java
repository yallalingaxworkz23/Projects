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

import com.xworkz.Pinxworkz.constants.InVoiceConstants;
import com.xworkz.Pinxworkz.dto.InVoiceDTO;
import com.xworkz.Pinxworkz.dto.ProdectDTO;
import com.xworkz.Pinxworkz.services.InVoiceService;
import com.xworkz.Pinxworkz.services.ProdectService;
import com.xworkz.Pinxworkz.services.VendorService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/")
public class InVoiceController {
	@Autowired
	private VendorService vmanagementService;

	@Autowired
	private ProdectService prodectService;

	@Autowired
	private InVoiceService inVoiceService;

	public InVoiceController() {
		log.info("running in the onvoice controller..");
	}

	// on submit generateInvoce from action is call action name as generateInvoice
	// id send InvocDTO
	@PostMapping("/generateInvoice")
	public String onInvoice(@Valid InVoiceDTO inVoicedto, BindingResult erroeinInvoice, Model model,RedirectAttributes redirectAttributes) {

		log.info(""+erroeinInvoice.hasErrors());
		log.info("Invoice Dto are .." + inVoicedto);
		if (erroeinInvoice.hasErrors()) {
			List<ObjectError> errorsList = erroeinInvoice.getAllErrors();
			errorsList.forEach(y -> System.err.println(y.getObjectName() + "message" + y.getDefaultMessage()));
			return "profilepage";
		} else {

			boolean anwer = this.inVoiceService.onSaveInVoice(inVoicedto);

			if (anwer == true) {
//				ProdectDTO prodectDTO=prodectService.findProductByOrderId(inVoicedto.getOrderId());
//				prodectDTO.setStatus(InVoiceConstants.Invoiced.toString());
//				prodectService.onUpdateProdect(prodectDTO);
				List<ProdectDTO> listOfProducts = prodectService.onListOfProdect();
				model.addAttribute("pdtos", listOfProducts);
                redirectAttributes.addFlashAttribute(inVoicedto);
				return "redirect:/profilepage";
			}
			return "profilepage";
		}

	}
	
	@GetMapping("/profilepage")
	private String returnpOnrofilepage(Model model) {
		List<ProdectDTO> listOfProducts = prodectService.onListOfProdect();
		model.addAttribute("pdtos", listOfProducts);
        
		return "profilepage";
	}
}
