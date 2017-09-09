package br.com.ab.Trello.test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Test;

import br.com.ab.Trello.dao.UserDao;
import br.com.ab.Trello.model.User;

public class UserTests {
	
	@Test
	public void addUserTest(){
		
		User user = new User("Andrey", "Test");
		UserDao userDao = new UserDao();
		
		EntityManager entityManager = new JPAUtil().getEntityManager();
		
		userDao.addUser(user, entityManager);
	}
	
	@Test
	public void findUserByName(){
		
		User user;
		UserDao userDao = new UserDao();
		
		user = userDao.findUserByLogin("Amanda");
		
		assertEquals("Amanda", user.getLogin());
		
	}
}
