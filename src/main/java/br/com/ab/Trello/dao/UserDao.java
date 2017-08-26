package br.com.ab.Trello.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;

import br.com.ab.Trello.model.User;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserDao {

	private EntityManager entityManager;
	
	public void addUser(User user){
		entityManager.persist(user);
	}
	
	public User findUserById(Integer userId){
		User user = this.entityManager.find(User.class, userId);
		return user;
	}
	
	public List<User> findAllUser(){
		return entityManager.createQuery("SELECT u FROM USER u", User.class).getResultList();
	}
}
