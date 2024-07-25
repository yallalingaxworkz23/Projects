package com.xworkz.Pinxworkz.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.xworkz.Pinxworkz.dto.AdminDTO;
import com.xworkz.Pinxworkz.dto.InVoiceDTO;
import com.xworkz.Pinxworkz.dto.ProdectDTO;
import com.xworkz.Pinxworkz.dto.VendorDTO;
import com.xworkz.Pinxworkz.services.AdminService;
import com.xworkz.Pinxworkz.services.InVoiceService;
import com.xworkz.Pinxworkz.services.ProdectService;
import com.xworkz.Pinxworkz.services.VendorService;

@Controller
@RequestMapping("/")
public class AdminController {
	
	@Autowired
	private ProdectService prodectService;
	
	@Autowired
	private InVoiceService inVoiceService;
	
   @Autowired
   private AdminService adminService;
   
   @Autowired
   private VendorService vservice;
	
	public AdminController() {
	       System.out.println("running in the admin Controller..");
	}

	
//HttpServletRequest ;- Extends the ServletRequest interface to provideRequest information for HTTP servlets. 
//	The servlet container creates an HttpServletRequestobject and passes it as an argument to the servlet's servicemethods (doGet, doPost, etc).

	@PostMapping("/adminlogin")
	public String onAdminLogin(String emailid,String password,Model model,HttpServletRequest request ) {
		System.out.println("getting email and password from admain.. "+emailid+""+password);
	     AdminDTO adminDTO=	 adminService.findByEmailidPassword(emailid, password);
	     if(adminDTO!=null) {
	    	 System.out.println("getting admin data from service.."+adminDTO);
	    	 
	    	 
	    	 
	    	 //using session we can access in any page..
	    	 //Provides a way to identify a user across more than one pagerequest or visit to a Web site and to store information about that user. 
            //The servlet container uses this interface to create a sessionbetween an HTTP client and an HTTP server. The session persistsfor a specified time period, across 

	    	 HttpSession requestforAdminEmail= request.getSession(true);
	    	 requestforAdminEmail.setAttribute("admimEmailid", emailid);
	    	 
	    	 model.addAttribute("adminDTO", adminDTO);
      	List<VendorDTO>  dtolist= vservice.onFindAllVendor();
      	model.addAttribute("vdto", dtolist);
      	     System.out.println("VenderDTO List.. are"+dtolist);
	    	 return "adminprofile";
	     }
	     model.addAttribute("adminerror", "Emailid or password invalid.");
		return "login";
	}
	
	@GetMapping("/approved")
	public String onApproved(String emailid,String adminEmail,Model model) {
		boolean isApporved=adminService.onApprovedOperaction(emailid,adminEmail);
		
		if(isApporved) {
			
			//model.addAttribute("afterApproveabBy", adminEmail);	
			List<VendorDTO>  dtolist= vservice.onFindAllVendor();
	      	model.addAttribute("vdto", dtolist);
			return "adminprofile";
		}
		return "adminprofile";
		
	}
	
	@GetMapping("/viewInvoice")
	public String onViewInvoice(Model model){
		List<InVoiceDTO> listOfInvoice= inVoiceService.onViewAllInvoiceByAdmin();
		model.addAttribute("viewInvoiceList", listOfInvoice);
		System.out.println("invoice list are in"+listOfInvoice);
		return "viewInVoice";
	}
	
	@GetMapping("/onOrder")
	public String onActionOrder(String orderId,Model model) {
		boolean isOrderd= adminService.onActionOrderInAdminService(orderId);
		if(isOrderd==true) {
			List<InVoiceDTO> listOfInvoice= inVoiceService.onViewAllInvoiceByAdmin();
			model.addAttribute("viewInvoiceList", listOfInvoice);
		return "viewInVoice";	
		}
		return "viewInVoice";
	}
	
	
	@GetMapping("/viewForProducts")
	public String onViewProdect(Model model){
		 List<ProdectDTO> listOfProducts= prodectService.onListOfProdect();
		  model.addAttribute("pdtos", listOfProducts);
		return "viewProdect";
	}
	
	
	@PostMapping("/updateProdect")
	public String onUpdateProdect(@Valid ProdectDTO prodectDTO,BindingResult forerrors , Model model) {
		System.out.println(forerrors.hasErrors());
		if(forerrors.hasErrors()) {
			List<ObjectError> erros= forerrors.getAllErrors();
			erros.forEach(y->System.err.println(y.getObjectName()+"message.."+y.getDefaultMessage()));
			List<ProdectDTO> listOfProducts= prodectService.onListOfProdect();
	 		  model.addAttribute("pdtos", listOfProducts);
	       
			return "viewProdect";
		}else {
		       boolean updated= prodectService.onUpdateProdect(prodectDTO);	
		       if(updated==true) {
		    	   List<ProdectDTO> listOfProducts= prodectService.onListOfProdect();
		 		  model.addAttribute("pdtos", listOfProducts);
		       }
		       return "viewProdect";
		}
		
		
	}
	
}
