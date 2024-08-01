package com.xworkz.Pinxworkz.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.Pinxworkz.entity.VendorEntity;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class VendorRepoImpl implements VendorRepo {

	@Autowired
	private EntityManagerFactory emf;

	@Override
	public boolean save(VendorEntity vmentity) {
		log.info("running in the repoimplementation..");
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();
			em.persist(vmentity);
			log.info("vmentity data is saved..");
			et.commit();

		} catch (PersistenceException pe) {
			log.info("getting persistence exception.." + pe.getMessage());
			et.getRollbackOnly();
			return false;
		} finally {
			log.info("closing em..");
			em.close();
		}

		return true;
	}

	@Override
	public VendorEntity findByContactNumber(String contactno) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Query query = em.createNamedQuery("findByContactNumber");
			query.setParameter("yk", contactno);
			Object object = query.getSingleResult();
			VendorEntity entity = (VendorEntity) object;
			log.info("getting single contact number from database ..");
			return entity;

		} catch (Exception pe) {
			log.info("getting persistence exception.." + pe);
			return null;
		} finally {
			em.close();
			log.info("closing the em from findByContactNumber.. ");
		}
	}

	@Override
	public VendorEntity findGstNo(String gstno) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			Query query = em.createNamedQuery("findByGstNo");
			query.setParameter("vgs", gstno);
			Object object = query.getSingleResult();
			VendorEntity entity = (VendorEntity) object;
			log.info("getting single gstno from database.");
			return entity;

		} catch (Exception pe) {
			log.info("getting persistance exception.." + pe.getMessage());
			return null;
		} finally {
			em.close();
		}

	}

	@Override
	public VendorEntity findByEmailid(String emailid) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		// log.info("1 to know which value is comeing.. "+emailid);
		try {
			et.begin();
			Query query = em.createNamedQuery("findByEmailid");
			query.setParameter("vem", emailid);

			// log.info("2 to know which valye is comeing.. "+emailid);

			Object object = query.getSingleResult();
			// log.info("to know the values after getting single
			// result,,"+object);

			VendorEntity entity = (VendorEntity) object;
			// log.info("to know the values after casting,,"+entity);

			log.info("getting single result from database.");
			return entity;

		} catch (Exception pe) {
			log.info("getting persistence exception.. " + pe);
			return null;
		} finally {
			em.close();
		}
	}

//	@Override // murge operaction for otp
//	public VendorEntity updateEntity(VendorEntity entity) {
//		EntityManager em = emf.createEntityManager();
//		EntityTransaction et = em.getTransaction();
//		try {
//			et.begin();
//			em.merge(entity);
//			et.commit();
//		} catch (Exception e) {
//			log.info("getting expextion for updateEntityForOtp.." + e.getMessage());
//			return null;
//		} finally {
//			em.clear();
//		}
//
//		return entity;
//	}

//	@Override
//	public VendorEntity findById(VendorEntity id) {
//		EntityManager em= emf.createEntityManager();
//		EntityTransaction et= em.getTransaction();
//		try {
//			et.begin();
//			Query query= em.createNamedQuery("findById");
//			query.setParameter("idn", id);
//			Object object= query.getSingleResult();
//			VendorEntity entity=(VendorEntity)object;
//			return entity;
//			
//		} catch (Exception e) {
//               log.info("getting exception in findById.."+e.getMessage());
//               return id;
//		}
//		finally {
//			em.close();
//		}
//		
//	}
//

	// saveing the data for update operactions..
	@Override
	public boolean updatAndSaveOperaction(VendorEntity ventity) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {
			et.begin();
			em.merge(ventity);
			et.commit();
			return true;
		} catch (Exception e) {
			log.info("getting expection in updatAndSaveOperaction.." + e.getMessage());
			return false;
		} finally {
			em.close();
		}

		
	}
	
	
	
	//to display all entity on admin profile page
	@Override
	public List<VendorEntity> onFindAllForAdmin() {
        EntityManager em=emf.createEntityManager();
         EntityTransaction et= em.getTransaction();
         try {
			et.begin();
 		   Query query=em.createNamedQuery("findAllForAdmin");
           List<VendorEntity> list=query.getResultList();
           
         return  list;
        	 
		} catch (Exception e) {
                 log.info("getting excpetion for onFindAllForAdmin.."+e.getMessage());
                 
		}finally {
			em.close();
		}
		return null;

	}
	
	@Override
	public void expireOTP() {


		EntityManager manager = emf.createEntityManager();
		try {
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			Query query = manager.createNamedQuery("expireOTP");
			query.setParameter("currentTime", LocalDateTime.now());
			query.executeUpdate();
			transaction.commit();
		} finally {
			manager.close();
		}

	}

	
}
	
	


