package br.com.ab.Trello.test;

import br.com.ab.Trello.dao.UserDao;
import br.com.ab.Trello.model.User;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserTests {

	private UserDao userDao;

	@Before
	public void setup() {
		userDao = new UserDao();
	}

	@Test(expected = ExceptionInInitializerError.class)
	public void addUserFailureTest() {

		User user = new User("Andrey", "Test");
		user.setId(1);

		EntityManager entityManager = new JPAUtil().getEntityManager();

		userDao.addUser(user, entityManager);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void addUserSucessfullyTest() {

		// Objeto EntityManager vai ser simulado pelo Mockito, na hora que executar o userDao.addUser;
		EntityManager entityManager = Mockito.mock(EntityManager.class);

		Mockito.doAnswer(new MockUserAnswer(1, "Andrey", "Test")).when(entityManager).persist(Mockito.any(User.class));

		// Precisa de get/set entityManager na userDao, para que tu possa criar o objeto entityManager com Mockito.mock;
		userDao.setEntityManager(entityManager);

		User user = new User("Andrey", "Test");
		userDao.addUser(user);

		Mockito.verify(userDao.getEntityManager()).persist(user);

		Assert.assertSame(user.getId(), 1);
		Assert.assertNotSame(user.getId(), 2);
	}

	@SuppressWarnings({ "unused", "deprecation" })
	@Test
	public void findUserByIdSucessfullyTest() {

		EntityManager entityManager = Mockito.mock(EntityManager.class);

		Mockito.doAnswer(new MockUserAnswer(1, "Andrey", "Test")).when(entityManager).persist(Mockito.any(User.class));
		
		userDao.setEntityManager(entityManager);
		
		// Creating and adding user, so we can test if findUserById will work.
		User user = new User("Andrey", "Test");
		userDao.addUser(user);
		
		Mockito.verify(userDao.getEntityManager()).persist(user);
		
		User userTest = userDao.findUserById(1);
		
		Assert.assertEquals("Andrey", userTest.getLogin());
		Assert.assertEquals("Test", userTest.getPassword());

	}

	private class MockUserAnswer implements Answer<Void> {
		private final Integer id;
		private final String login;
		private final String pass;

		// Construtor da classe.
		private MockUserAnswer(Integer id, String login, String pass) {
			this.id = id;
			this.login = login;
			this.pass = pass;
		}

		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			User user = (User) invocation.getArgument(0);
			user.setId(id);
			user.setLogin(login);
			user.setPassword(pass);
			return null;
		}
	}
}
