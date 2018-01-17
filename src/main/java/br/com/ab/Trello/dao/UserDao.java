package br.com.ab.Trello.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.ab.Trello.model.User;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserDao {

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
	
	public void addUser(User user){
		this.entityManager.persist(user);
	}
	
	public User findUserById(Integer userId){
		User user = this.entityManager.find(User.class, userId);
		return user;
	}
	
	public User findUserByLogin(String userLogin){
		User user = this.entityManager.find(User.class, userLogin);
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUser(){
		// Here is the name of the class! Not table! So its User.
		return entityManager.createQuery("SELECT u FROM User u").getResultList();
	}
}
