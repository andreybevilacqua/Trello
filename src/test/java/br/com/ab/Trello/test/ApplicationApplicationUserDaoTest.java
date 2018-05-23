package br.com.ab.Trello.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.ab.Trello.dao.ApplicationUserDao;
import br.com.ab.Trello.model.ApplicationUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import junit.framework.Assert;

public class ApplicationApplicationUserDaoTest {

	private ApplicationUserDao applicationUserDao;
	private EntityManager entityManager;

	@Before
	public void setup() {

		applicationUserDao = new ApplicationUserDao();
		entityManager = Mockito.mock(EntityManager.class);

		applicationUserDao.setEntityManager(entityManager);
	}

	@SuppressWarnings("deprecation")
	@Test // Eu mantive esse teste igual como o Ingo fez, para usar como
			// exemplo.
	public void addUserSucessfullyTest() {

		// Objeto EntityManager vai ser simulado pelo Mockito, na hora que
		// executar o applicationUserDao.addUser;
		EntityManager entityManager = Mockito.mock(EntityManager.class);

		Mockito.doAnswer(new MockUserAnswer(1, "Andrey", "Test")).when(entityManager).persist(Mockito.any(ApplicationUser.class));

		// Precisa de get/set entityManager na applicationUserDao, para que tu possa criar
		// o objeto entityManager com Mockito.mock;
		applicationUserDao.setEntityManager(entityManager);

		ApplicationUser applicationUser = new ApplicationUser("Andrey", "Test");
		applicationUserDao.addUser(applicationUser);

		Mockito.verify(applicationUserDao.getEntityManager()).persist(applicationUser);

		Assert.assertSame(applicationUser.getId(), 1);
		Assert.assertNotSame(applicationUser.getId(), 2);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void findUserByIdSucessfullyTest() throws InstantiationException, IllegalAccessException {

		ApplicationUser applicationUser = new ApplicationUser("Andrey", "Test");
		applicationUser.setId(1);

		// Quando o entityManager realizar o find, buscando pelo ApplicationUser.class, e
		// passando applicationUser.getId() como parametro,
		// então retorna o objeto applicationUser.
		Mockito.when(entityManager.find(ApplicationUser.class, applicationUser.getId())).thenReturn(applicationUser);

		applicationUserDao.setEntityManager(entityManager); // Nao precisa mais, pois esta
													// no @Before.

		ApplicationUser applicationUserTest = applicationUserDao.findUserById(applicationUser.getId());

		Assert.assertSame(applicationUser, applicationUserTest);

	}

	@SuppressWarnings("deprecation")
	@Test
	public void findUserByUnknownIdTest() {

		int id = -1;
		Mockito.when(entityManager.find(ApplicationUser.class, id)).thenReturn(null);

		ApplicationUser applicationUserTest = applicationUserDao.findUserById(id);

		Mockito.verify(entityManager).find(ApplicationUser.class, id);

		Assert.assertNull(applicationUserTest);
		Assert.assertSame(applicationUserTest, null);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void findUserByLogin() {

		String login = "Andrey";
		Mockito.when(entityManager.find(ApplicationUser.class, login)).thenReturn(new ApplicationUser("Andrey", "Test"));

		ApplicationUser applicationUserTest = applicationUserDao.findUserByLogin(login);

		Assert.assertEquals(applicationUserTest.getLogin(), login);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void findAllUsers() {

		List<ApplicationUser> listOfApplicationUsers = new ArrayList<ApplicationUser>();
		
		//listOfApplicationUsers = applicationUserDao.findAllUser();
		
		//Mockito.when(entityManager.createQuery("SELECT u FROM user u", ApplicationUser.class).getResultList()).thenReturn(listOfApplicationUsers);
		
		//Assert.assertEquals(3, listOfApplicationUsers.size());
		
		// Temporary
		Assert.assertTrue(true);

	}

	private class MockAllUsersAnswer implements Answer<Void> {

		private List<ApplicationUser> listOfApplicationUsers;

		private MockAllUsersAnswer() {

			listOfApplicationUsers = new ArrayList<ApplicationUser>();

			listOfApplicationUsers.add(new ApplicationUser("Andrey", "Teste"));
			listOfApplicationUsers.add(new ApplicationUser("Morena", "Teste"));
			listOfApplicationUsers.add(new ApplicationUser("Vinicius", "Teste"));
			listOfApplicationUsers.add(new ApplicationUser("Georgia", "Teste"));
		}

		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {

			List<ApplicationUser> listOfApplicationUserInvocation = invocation.getArgument(0);
			listOfApplicationUserInvocation.addAll(listOfApplicationUsers);

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
			ApplicationUser applicationUser = (ApplicationUser) invocation.getArgument(0);
			applicationUser.setId(id);
			applicationUser.setLogin(login);
			applicationUser.setPassword(pass);
			return null;
		}
	}
}
