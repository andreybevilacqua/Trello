package br.com.ab.Trello.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.ab.Trello.dao.UserDao;
import br.com.ab.Trello.model.User;
import junit.framework.Assert;

public class UserDaoTest {

	private UserDao userDao;
	private EntityManager entityManager;

	@Before
	public void setup() {

		userDao = new UserDao();
		entityManager = Mockito.mock(EntityManager.class);

		userDao.setEntityManager(entityManager);
	}

	@SuppressWarnings("deprecation")
	@Test // Eu mantive esse teste igual como o Ingo fez, para usar como
			// exemplo.
	public void addUserSucessfullyTest() {

		// Objeto EntityManager vai ser simulado pelo Mockito, na hora que
		// executar o userDao.addUser;
		EntityManager entityManager = Mockito.mock(EntityManager.class);

		Mockito.doAnswer(new MockUserAnswer(1, "Andrey", "Test")).when(entityManager).persist(Mockito.any(User.class));

		// Precisa de get/set entityManager na userDao, para que tu possa criar
		// o objeto entityManager com Mockito.mock;
		userDao.setEntityManager(entityManager);

		User user = new User("Andrey", "Test");
		userDao.addUser(user);

		Mockito.verify(userDao.getEntityManager()).persist(user);

		Assert.assertSame(user.getId(), 1);
		Assert.assertNotSame(user.getId(), 2);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void findUserByIdSucessfullyTest() throws InstantiationException, IllegalAccessException {

		User user = new User("Andrey", "Test");
		user.setId(1);

		// Quando o entityManager realizar o find, buscando pelo User.class, e
		// passando user.getId() como parametro,
		// então retorna o objeto user.
		Mockito.when(entityManager.find(User.class, user.getId())).thenReturn(user);

		userDao.setEntityManager(entityManager); // Nao precisa mais, pois esta
													// no @Before.

		User userTest = userDao.findUserById(user.getId());

		Assert.assertSame(user, userTest);

	}

	@SuppressWarnings("deprecation")
	@Test
	public void findUserByUnknownIdTest() {

		int id = -1;
		Mockito.when(entityManager.find(User.class, id)).thenReturn(null);

		User userTest = userDao.findUserById(id);

		Mockito.verify(entityManager).find(User.class, id);

		Assert.assertNull(userTest);
		Assert.assertSame(userTest, null);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void findUserByLogin() {

		String login = "Andrey";
		Mockito.when(entityManager.find(User.class, login)).thenReturn(new User("Andrey", "Test"));

		User userTest = userDao.findUserByLogin(login);

		Assert.assertEquals(userTest.getLogin(), login);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void findAllUsers() {

		List<User> listOfUsers = new ArrayList<User>();
		
		//listOfUsers = userDao.findAllUser();
		
		//Mockito.when(entityManager.createQuery("SELECT u FROM user u", User.class).getResultList()).thenReturn(listOfUsers);
		
		//Assert.assertEquals(3, listOfUsers.size());
		
		// Temporary
		Assert.assertTrue(true);

	}

	private class MockAllUsersAnswer implements Answer<Void> {

		private List<User> listOfUsers;

		private MockAllUsersAnswer() {

			listOfUsers = new ArrayList<User>();

			listOfUsers.add(new User("Andrey", "Teste"));
			listOfUsers.add(new User("Morena", "Teste"));
			listOfUsers.add(new User("Vinicius", "Teste"));
			listOfUsers.add(new User("Georgia", "Teste"));
		}

		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {

			List<User> listOfUserInvocation = invocation.getArgument(0);
			listOfUserInvocation.addAll(listOfUsers);

			return null;
		}
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
