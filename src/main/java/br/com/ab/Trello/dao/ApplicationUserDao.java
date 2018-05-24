package br.com.ab.Trello.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ab.Trello.model.ApplicationUser;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ApplicationUserDao {

	@PersistenceContext(unitName = "Trello")
	EntityManager entityManager;
	
	// Mock tests.
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	// Mock tests.
	public EntityManager getEntityManager(){
		return this.entityManager;
	}
	
	public void addUser(ApplicationUser applicationUser){
		this.entityManager.persist(applicationUser);
	}
	
	public ApplicationUser findUserById(Integer userId){
		ApplicationUser applicationUser = this.entityManager.find(ApplicationUser.class, userId);
		return applicationUser;
	}
	
	public ApplicationUser findUserByLogin(String userLogin){
		ApplicationUser applicationUser = this.entityManager.find(ApplicationUser.class, userLogin);
		return applicationUser;
	}
	
	@SuppressWarnings("unchecked")
	public List<ApplicationUser> findAllUser(){
		return entityManager.createQuery("SELECT u FROM ApplicationUser u").getResultList();
	}
}
