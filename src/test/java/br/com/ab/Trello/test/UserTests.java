package br.com.ab.Trello.test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.mockito.Mockito;

import br.com.ab.Trello.dao.UserDao;
import br.com.ab.Trello.model.User;
import junit.framework.Assert;

public class UserTests {

	@Test(expected = ExceptionInInitializerError.class)
	public void addUserFailureTest() {

		UserDao userDao = new UserDao();

		User user = new User("Andrey", "Test");
		user.setId(1);

		EntityManager entityManager = new JPAUtil().getEntityManager();

		userDao.addUser(user, entityManager);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void addUserSucessfullyTest() {

		UserDao userDao = new UserDao();
		userDao.setEntityManager(Mockito.mock(EntityManager.class));

		// ArgumentCaptor<SuccessfulPaymentEvent> argumentCaptor =
		// ArgumentCaptor.forClass(SuccessfulPaymentEvent.class);
		User user = new User("Andrey", "Test");
		userDao.addUser(user);

		Mockito.verify(userDao.getEntityManager()).persist(user);

		int user_Id = 14;

		Assert.assertEquals(user.getId().intValue(), user_Id);
	}

	@Test
	public void testDBConnection(){
		
		EntityManager entityManager = Mockito.mock(EntityManager.class);
		String usernameTest = "Andrey";
		String databaseReturn = (String)entityManager.createQuery("SELECT username FROM user WHERE user_id = 1").getSingleResult();
		
		assertEquals(usernameTest, databaseReturn);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
