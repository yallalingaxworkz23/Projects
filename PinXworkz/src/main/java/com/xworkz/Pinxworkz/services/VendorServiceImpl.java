package com.xworkz.Pinxworkz.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.Email;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.xworkz.Pinxworkz.constants.VendorConstants;
import com.xworkz.Pinxworkz.dto.VendorDTO;
import com.xworkz.Pinxworkz.entity.VendorEntity;
import com.xworkz.Pinxworkz.repository.VendorRepo;

@Service
public class VendorServiceImpl  implements VendorService {
	
	@Autowired
	private VendorRepo vmanagementrepo;

	@Override
	public boolean saveOperation(VendorDTO vdto) {

		VendorEntity vendorEntity = new VendorEntity();	
		vdto.setCreatedby(vdto.getEmailid());
		vdto.setCreatedate(LocalDate.now().toString());
		vdto.setStatus(VendorConstants.PENDING.toString());
	//seting the value to createdby  by emailid  by using set to get	
		BeanUtils.copyProperties(vdto, vendorEntity);
		
		
		System.out.println("saving and connecting data from Venderdto to venderentity ");
		System.out.println("connectin service to repository..");
		boolean isSaved = vmanagementrepo.save(vendorEntity);
		if(isSaved) {
			sendmail(vdto.getEmailid(),"verification mail","you are registration form is submited thank you..");
		}
		return isSaved;
	}

	@Async
	@Override
	public boolean sendmail(String mail,String subject,String body) {

		String portNumber = "587";
		String hostName = "smtp.gmail.com";
		String fromEmail = "yallu3630@gmail.com";
		String password = "yokn fugk bexk mvcg";
		String to = mail;

		Properties prop = new Properties();

		prop.put("mail.smtp.host", hostName);
		prop.put("mail.smtp.port", portNumber);
		prop.put("mail.smtp.starttls.enable", true);
		prop.put("mail.debug", true);
		prop.put("mail.smtp.auth", true);
		prop.put("mail.transport.protocol", "smtp");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});

		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(fromEmail));
			message.setSubject(subject);
			message.setText(body);

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport.send(message);
			System.out.println("email sent successfully..");
//			vmanagementrepo.findByEmailid(fromEmail);
			return true;

		}

		catch (MessagingException e) {

			e.printStackTrace();
		}

		return false;
	}

	@Override
	public String generateOTP(String emailid) {
		VendorEntity vendorEntity = vmanagementrepo.findByEmailid(emailid);

		if (vendorEntity != null) {
			String otp = generateRandomPassword();
             vendorEntity.setOtp(otp);
             vmanagementrepo.updatAndSaveOperaction(vendorEntity);
			boolean mailSent= sendmail(emailid, "your otp for login", otp);
			if(mailSent) {
	            return "otp sent to mail";  
			}
			else {
				return "otp not sent";
			} 
		}
		
		return " don't have account please register";
	}
	
	

	

	public String generateRandomPassword() {

		PasswordGenerator passGen = new PasswordGenerator();

		CharacterRule lcr = new CharacterRule(EnglishCharacterData.LowerCase);
		lcr.setNumberOfCharacters(2);

		CharacterRule dr = new CharacterRule(EnglishCharacterData.Digit);
		dr.setNumberOfCharacters(2);

		String randomPassword = passGen.generatePassword(8, lcr, dr);
		return randomPassword;

	}

	@Override
	public String otpValidation(String emailid, String otp) {
              VendorEntity entity=  vmanagementrepo.findByEmailid(emailid);
            if(entity!=null) { 
            	
              if( entity.getOtp().equals(otp)) {
            	  //to chuck time is notnull  and to know time  2:30 is locked and again login 2:31 it not login bacause locked time is 2 minites   
            	  if(entity.getAccountLockTime()!=null && entity.getAccountLockTime().isAfter(LocalDateTime.now())) {
                		return "Account Locked try after 2 minutes";
            	  }
            	  entity.setLoginCount(0);
            	  entity.setAccountLockTime(null);
            	  vmanagementrepo.updatAndSaveOperaction(entity);  
            	  return "Valid OTP";
              }
             
                 if(entity.getLoginCount()>=3) {
            	     if(entity.getAccountLockTime()==null) {
            	       entity.setAccountLockTime(LocalDateTime.now().plusMinutes(2l));
            	       vmanagementrepo.updatAndSaveOperaction(entity);
            	      }
          		return "Account Locked try after 2 minutes";
                 }
              
              entity.setLoginCount(entity.getLoginCount()+1);
              vmanagementrepo.updatAndSaveOperaction(entity);
              System.out.println("otp is not valid..");
              return "invalid OTP";
            }
            return null;
	}

	
//using findbyemailid retriving the entity and converting from entity to dto for display the data in editpage;	
	@Override
	public VendorDTO findByEmailID(String emailid) {
		System.out.println("find by emaliid for do update operaction..");
		 
			VendorEntity entity= vmanagementrepo.findByEmailid(emailid);
			VendorDTO vdto = new VendorDTO();
		BeanUtils.copyProperties( entity,vdto);
	
		return vdto;
	}

	
	
	@Override
	public boolean afterUpdateSaveOperaction(VendorDTO vdto) {
      
		VendorEntity entity=new VendorEntity();
		
//		System.out.println("afterUpdateSaveOperaction"+id);
		
		vdto.setUpdatedby(vdto.getEmailid());
		vdto.setUpdateddate(LocalDate.now().toString());
		vdto.setStatus(VendorConstants.PENDING.toString());
		BeanUtils.copyProperties(vdto, entity);
	//	VendorEntity entity2= vmanagementrepo.findById(entity);
	//	vdto.setId(entity2.getId());
		
		System.out.println("connecting and saving the  afterUpdateSaveOperaction data from Vdto to entity..");
	//	if(entity2.equals(vdto.getId())) {
		
			boolean updateandsave= vmanagementrepo.updatAndSaveOperaction(entity);	
		
		
	//	}
		return updateandsave;
	}

	@Override
	public List<VendorDTO> onFindAllVendor() {
   //came list of entitys from repo and and store in  listOfEntities
		
            List<VendorEntity> listOfEntities=  vmanagementrepo.onFindAllForAdmin();
            List<VendorDTO> dtoList=new ArrayList<VendorDTO>();
            
            
   // during for each method store  single entity in (entity->) and storing entity to dto.          
            listOfEntities.forEach(entity->{
            	  VendorDTO vdto=new VendorDTO(); 
                  BeanUtils.copyProperties(entity, vdto);
                  dtoList.add(vdto);
                  System.out.println("to know values are adding are not.."+dtoList);
                		  });
         
           
		
		return dtoList;
	}

	@Scheduled(fixedDelay = 1000, initialDelay = 1000)
	@Override
	public void setLoginCountZero() {
		vmanagementrepo.expireOTP();
	}
	

}
