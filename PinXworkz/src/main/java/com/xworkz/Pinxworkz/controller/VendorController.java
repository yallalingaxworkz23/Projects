package com.xworkz.Pinxworkz.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.mail.Multipart;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xworkz.Pinxworkz.dto.ProdectDTO;
import com.xworkz.Pinxworkz.dto.VendorDTO;
import com.xworkz.Pinxworkz.services.ProdectService;
import com.xworkz.Pinxworkz.services.VendorService;

import lombok.extern.slf4j.Slf4j;

@Controller // it returns pages
@RequestMapping("/")
@Slf4j
public class VendorController {

	@Autowired
	private ProdectService prodectService;

	@Autowired
	private VendorService vmanagementService;

	public VendorController() {
		log.info("controller is created...");
	}

	@PostMapping("/vmanagemnet")
	public String onSaveVender(@Valid VendorDTO vdto, BindingResult binderrors, Model modetext,
			RedirectAttributes redirectAttributes) {
		log.info(""+binderrors.hasErrors());
		modetext.addAttribute("vmentity", vdto);
		log.info("mapping is occurs for.." + vdto);

		if (binderrors.hasErrors()) {
			List<ObjectError> objerrors = binderrors.getAllErrors();
			objerrors.forEach(y -> System.err.println(y.getObjectName() + "message" + y.getDefaultMessage()));
			return "rigester";

		} else {
			log.info("saving from controller..");
			this.vmanagementService.saveOperation(vdto);
			
			// prg design pattern by using this we can controll the form resubminaction by refresh
			redirectAttributes.addFlashAttribute(vdto);
			return "redirect:/rigestersuccess";
		}

	}

	@GetMapping("/rigestersuccess")
	private String rigestersuccess() {

		return "rigestersuccess";
	}

	@PostMapping("/vmanagemnetlogin")
	public String onLogIn(String emailid, String otp, Model model, HttpServletRequest request) {
		String forotpvalidation = vmanagementService.otpValidation(emailid, otp);
		if (forotpvalidation != null) {
			if (forotpvalidation.equals("Valid OTP")) {
				log.info("otp is valid return success page..");
                VendorDTO vdto = vmanagementService.findByEmailID(emailid);
				HttpSession httpsession = request.getSession(true);
				httpsession.setAttribute("venderEmaild", emailid);
				httpsession.setAttribute("picPath", vdto.getPicName());

				model.addAttribute("username", emailid); // printing tha emailid in user_profile page..

				// to get all list of Requiremmtn-prodects list for vender to show in vender
				// profile page..
				List<ProdectDTO> listOfProducts = prodectService.onListOfProdect();
				model.addAttribute("pdtos", listOfProducts);
				log.info("to know the prodect list are coming.." + listOfProducts);

				return "profilepage";
			}

		}
		model.addAttribute("email", emailid);
		model.addAttribute("forotp", forotpvalidation);
		return "login";
	}

	// by clicking edit geting tha all data fecting from database using refrence
	// emailid and display in editpage;
	@GetMapping("/edit")
	public String forUpdate(String email, Model model) {

		VendorDTO vdto = vmanagementService.findByEmailID(email);
		// model.addAttribute("username",email);
		// printing all fields in the updte profile page
		model.addAttribute("vdata", vdto);
		return "edit";
	}

	@PostMapping("/update")
	public String upDateAndSaveOperaction(@Valid VendorDTO vdto, BindingResult bindinerrors, Model model,
			HttpServletRequest request,RedirectAttributes redirectAttributes) {
		log.info("update" + vdto + "to know id is comming are not");

		boolean isUpdated = vmanagementService.afterUpdateSaveOperaction(vdto);
		redirectAttributes.addFlashAttribute(vdto);
		log.info("in controller action update  " + vdto);
		model.addAttribute("data", vdto);

		if (isUpdated == true) {
			model.addAttribute("msg", "your profile is updated");

			HttpSession httpsession = request.getSession(true);
			httpsession.setAttribute("venderEmaild", vdto.getEmailid());

			List<ProdectDTO> onupdateListOfProdects = prodectService.onListOfProdect();
			model.addAttribute("pdtos", onupdateListOfProdects);
			return "redirect:/profilepage";
		}
		model.addAttribute("msg", "error while updating data");
		return "edit";

	}
	
//	@GetMapping("/profilepage")
//	private String onRedirectUpdate(HttpServletRequest request,Model model) {
//		VendorDTO vdto=new VendorDTO();
//		HttpSession httpsession = request.getSession(true);
//		httpsession.setAttribute("venderEmaild", vdto.getEmailid());
//
//		List<ProdectDTO> onupdateListOfProdects = prodectService.onListOfProdect();
//		model.addAttribute("pdtos", onupdateListOfProdects);
//		
//		return "profilepage";
//	}
	
	@PostMapping("/uploadUserImage")
	public String onUploadUserImage(@RequestParam MultipartFile image,String emailid,HttpServletRequest request,Model model,RedirectAttributes redirectAttributes) {
		log.info("getting in uploadUserImage controller..");
		VendorDTO vdto= vmanagementService.findByEmailID(emailid);
		try {
			byte[] bytes = ((MultipartFile) image).getBytes();
			Path path = Paths.get("E:\\users-images\\" + emailid + System.currentTimeMillis() + ((MultipartFile) image).getOriginalFilename());
			Files.write(path, bytes);

			vdto.setPicName(path.getFileName().toString());
			vmanagementService.afterUpdateSaveOperaction(vdto);
			redirectAttributes.addFlashAttribute(vdto);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("getting expection in uplodeUserImage Contoller.."+e.getMessage());
			
		}
		
		HttpSession httpsession = request.getSession(true);
		httpsession.setAttribute("venderEmaild", vdto.getEmailid());
		httpsession.setAttribute("picPath", vdto.getPicName());

		List<ProdectDTO> onupdateListOfProdects = prodectService.onListOfProdect();
		model.addAttribute("pdtos", onupdateListOfProdects);
//		return "redirect:/profilepage";
		return "profilepage";
		
	}
	
//	@GetMapping("/profilepage")
//    private String forRedirectProfilePage(HttpServletRequest request,Model model) {
//		
//		VendorDTO vdto=new VendorDTO();
//		
//		HttpSession httpsession = request.getSession(true);
//		httpsession.setAttribute("venderEmaild", vdto.getEmailid());
//		httpsession.setAttribute("picPath", vdto.getPicName());
//		
//		List<ProdectDTO> onupdateListOfProdects = prodectService.onListOfProdect();
//		model.addAttribute("pdtos", onupdateListOfProdects);
//	   return "profilepage";
//   }
	
	
	
	@GetMapping("/display")
	public void onDownload(HttpServletResponse response, @RequestParam String fileName, VendorDTO vdto)
			throws IOException {
        System.err.println("================================================"+fileName);
		response.setContentType("image/jpeg");
		File file = new File("E:\\users-images\\" + fileName);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ServletOutputStream out = response.getOutputStream();
		IOUtils.copy(in, out);
		response.flushBuffer();
	}
	

	@GetMapping("/backtovprofilep")
	public String onbacktovenderprofilepage(Model model) {
		List<ProdectDTO> listOfProducts = prodectService.onListOfProdect();
		model.addAttribute("pdtos", listOfProducts);
		
		return "profilepage";
	}
	
	@GetMapping("/backToVenderProfile")
	public String onBackToProfilePage(Model model) {
		List<ProdectDTO> listOfProducts = prodectService.onListOfProdect();
		model.addAttribute("pdtos", listOfProducts);
		
		return "profilepage";
	}
	
	
	
}
