package com.xworkz.Pinxworkz.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.Pinxworkz.entity.InVoiceEntity;

@Repository
public class InVoiceRepoImpl implements InVoiceRepo{

	@Autowired
	private EntityManagerFactory emf;
	
	@Override
	public boolean onSaveInVoiceRepo(InVoiceEntity inVoiceEntity) {
		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		try {
			et.begin();
			em.persist(inVoiceEntity);
			et.commit();
			
		} catch (Exception e) {
          System.out.println("getting expection..from invoiceRepo.."+e.getMessage());
      return false;
		}
		finally {
			em.close();
		}
		return true;
	}

	@Override
	public InVoiceEntity onViewInvoiceInRepo(String orderId) {
		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		try {
			et.begin();
		 Query query=em.createNamedQuery("findByOrderIdForInvoice");
		  query.setParameter("invgenrated", orderId);
		  Object invoice= query.getSingleResult();
		  InVoiceEntity inVoiceEntity=(InVoiceEntity) invoice;
		  return inVoiceEntity;
			
		} catch (Exception e) {
           System.out.println("getting exception in onViewInvoice.. "+e.getMessage());
		}
		finally {
			em.close();
		}
		
		return null;
	}

	@Override
	public List<InVoiceEntity> onViewInvoiceAdmin() {
         EntityManager em= emf.createEntityManager();
         EntityTransaction et= em.getTransaction();
         try {
			et.begin();
			Query allviewquery= em.createNamedQuery("vewAllInVoice");
			List<InVoiceEntity> listofInvoice= allviewquery.getResultList();
			
			return listofInvoice;
		} catch (Exception e) {
			System.out.println("getting exception in onViewInvoiceByAdmin .."+e.getMessage());
			return null;
		}
         finally {
        	 em.close();
         }
		
	}

	@Override
	public boolean onActionOrderUpdateOperaction(InVoiceEntity inVoiceEntity) {

		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		try {
			et.begin();
			em.merge(inVoiceEntity);
			et.commit();
			return true;
		} catch (Exception e) {
         System.out.println("getting exception form onActionOrderUpdateOperaction method.."+e.getMessage());
         return false;
		}
		finally {
			em.close();
		}
		
		
	}

}
