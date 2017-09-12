package br.com.ab.Trello.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	public void addUser(User user, EntityManager entityManager){
		entityManager.persist(user);
	}
	
	public User findUserById(Integer userId){
		User user = this.entityManager.find(User.class, userId);
		return user;
	}
	
	public User findUserByLogin(String userLogin){
		User user = this.entityManager.find(User.class, userLogin);
		return user;
	}
	
	public List<User> findAllUser(){
		return this.entityManager.createQuery("SELECT u FROM USER u", User.class).getResultList();
	}
}
