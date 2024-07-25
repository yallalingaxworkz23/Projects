package com.xworkz.Pinxworkz.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.Pinxworkz.entity.ProdectEntity;

@Repository
public class ProdectRepoImpl implements ProdectRepo{

	@Autowired 
	private  EntityManagerFactory emf;
	
	@Override
	public boolean onProdectSaveRepo(ProdectEntity prodectEntity) {
		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		try {
			et.begin();
			em.persist(prodectEntity);
			et.commit();
		} catch (Exception e) {
           System.out.println("getting exception for prodect.."+e.getMessage());
		}
		finally {
			em.close();
		}

		return true;
	}

	@Override
	public List<ProdectEntity> findAllProdects() {
		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		try {
			et.begin();
			Query  query=em.createNamedQuery("allProdects");
			List<ProdectEntity> listofProduct =query.getResultList();
			System.out.println(listofProduct);
			return listofProduct;
			
		} catch (Exception e) {
              System.out.println("getting expection.."+e.getMessage());
              
		}
		finally {
			em.close();
		}
		
		return null;
	}

	@Override
	public ProdectEntity findProductOnOderId(String orderId) {
         EntityManager em=emf.createEntityManager();
         EntityTransaction et= em.getTransaction();
         try {
        	 et.begin();
        	 Query query= em.createNamedQuery("findProdectByOrderId");
        	 query.setParameter("orderid",orderId);
        	 Object object= query.getSingleResult();
        	 ProdectEntity entity=(ProdectEntity)object;
        	 return entity;
			
		} catch (Exception e) {
			System.out.println("getting expection..in .findProductOnOderId.."+e.getMessage());
			return null;
		}
         finally {
        	 em.close();
         }
         
		
	}

	@Override
	public boolean onUpdateProdect(ProdectEntity prodectEntity) {
		EntityManager em= emf.createEntityManager();
		EntityTransaction et= em.getTransaction();
		try {
			et.begin();
			em.merge(prodectEntity);
			et.commit();
			return true;
		} catch (Exception e) {
			System.out.println("getting exception on onUpdateProdect method in repo.."+e.getMessage());
			return false;
		}
		finally {
			em.close();
		}
		
		
	}

}
