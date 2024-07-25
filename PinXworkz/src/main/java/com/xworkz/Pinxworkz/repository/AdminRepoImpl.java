package com.xworkz.Pinxworkz.repository;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.Pinxworkz.entity.AdminEntity;
import com.xworkz.Pinxworkz.entity.VendorEntity;

@Repository
public class AdminRepoImpl implements AdminRepo {

	@Autowired
	private VendorRepo vendorRepo;
	
	@Autowired
	private EntityManagerFactory emf;

	@Override
	public AdminEntity findByEmailidAndPassword(String emailid, String password) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			System.err.println(emailid+"         "+password);
			Query query = em.createNamedQuery("adminLogin");
			query.setParameter("email", emailid);
			query.setParameter("password", password);
			System.err.println(query);
			 Object object= query.getSingleResult();
			System.err.println("values of object." + object);
			AdminEntity adminEntity=(AdminEntity)object;
              System.out.println( "to know data .. from query."+adminEntity);
			return adminEntity;

		} catch (Exception e) {
			System.out.println("getting exception in adminRepoImpl.." + e.getMessage());
			return null;
		} finally {
			em.close();
		}

	}

	

	
}
