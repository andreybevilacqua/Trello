package br.com.ab.Trello.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Trello");
	
	public EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
}
