package com.xworkz.Pinxworkz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.Pinxworkz.dto.ProdectDTO;
import com.xworkz.Pinxworkz.dto.VendorDTO;
import com.xworkz.Pinxworkz.services.ProdectService;
import com.xworkz.Pinxworkz.services.VendorService;

@Controller  //it returns pages
@RequestMapping("/")
public class VendorController {
	
	@Autowired
	private ProdectService prodectService;
	
	@Autowired
	private VendorService vmanagementService;
	
	public VendorController() {
	System.out.println("controller is created...");
	}
	
	@PostMapping("/vmanagemnet")
	public String onSaveVender(@Valid VendorDTO vdto,BindingResult binderrors,Model modetext) {
		System.out.println(binderrors.hasErrors());
		modetext.addAttribute("vmentity",vdto);
		System.out.println("mapping is occurs for.."+vdto);
		
		if(binderrors.hasErrors()) {
			 List<ObjectError> objerrors= binderrors.getAllErrors();
			 objerrors.forEach(y->System.err.println(y.getObjectName()+"message"+y.getDefaultMessage()));
			 return "rigester";
			 
		}else{
			System.out.println("saving from controller..");
			this.vmanagementService.saveOperation(vdto);
			return "rigestersuccess";
		}	
		
	}
	
	
	
	@PostMapping("/vmanagemnetlogin")
	public String onLogIn(String emailid,String otp,Model model,HttpServletRequest request) {
	    String  forotpvalidation=vmanagementService.otpValidation(emailid, otp);
	   if(forotpvalidation!=null) { 
		 if(forotpvalidation.equals("Valid OTP")) {
			 System.out.println("otp is valid return success page..");
			 
			 HttpSession httpsession= request.getSession(true);
			 httpsession.setAttribute("venderEmaild", emailid);
			 model.addAttribute("username",emailid);  //printing tha emailid in user_profile page..
			 
			//to get all list of prodect for vender  to show in vender profile page..
			  List<ProdectDTO> listOfProducts= prodectService.onListOfProdect();
			  model.addAttribute("pdtos", listOfProducts);
			  System.out.println("to know the prodect list are coming.."+listOfProducts);

			 return "profilepage";
		 }
		
	   }
	   model.addAttribute("email",emailid);  
		 model.addAttribute("forotp" ,forotpvalidation);
		 return "login";
	}
	
	
	
	// by clicking edit geting tha all data fecting from database using refrence emailid and display in editpage;
	@GetMapping("/edit")
	public String forUpdate(String email,Model model) {
	    
			VendorDTO vdto= vmanagementService.findByEmailID(email);
			 //model.addAttribute("username",email);
			//printing all fields in the updte profile page
			 model.addAttribute("vdata",vdto);
			 return "edit";
	}
	
	
	
	@PostMapping("/update")
	public String upDateAndSaveOperaction(@Valid VendorDTO vdto,BindingResult bindinerrors,Model model,HttpServletRequest request) {
		System.out.println("update"+vdto+"to know id is comming are not");
		
	  	boolean isUpdated=vmanagementService.afterUpdateSaveOperaction(vdto);
		System.out.println("in controller action update  "+vdto);
		 model.addAttribute("data",vdto);
		 
		if(isUpdated==true) {
			model.addAttribute("msg","your profile is updated");
			
			HttpSession httpsession= request.getSession(true);
			 httpsession.setAttribute("venderEmaild", vdto.getEmailid());
			 
			 List<ProdectDTO> onupdateListOfProdects= prodectService.onListOfProdect();
             model.addAttribute("pdtos", onupdateListOfProdects);
			return "profilepage";
		}
		model.addAttribute("msg","error while updating data");
		return "edit";
		
	}

	
	
}
