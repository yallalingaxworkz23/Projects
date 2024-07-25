package com.xworkz.Pinxworkz.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryUtil {

	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getemf() {
		return emf;
	}
	static {
		emf=Persistence.createEntityManagerFactory("x-workz");
	}
}
