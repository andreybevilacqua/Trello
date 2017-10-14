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
import javax.persistence.Query;

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

        EntityManager entityManager = Mockito.mock(EntityManager.class);

        Mockito.doAnswer(new MockUserAnswer(1, "Andrey", "Test"))
                .when(entityManager)
                .persist(Mockito.any(User.class));

        userDao.setEntityManager(entityManager);

        // ArgumentCaptor<SuccessfulPaymentEvent> argumentCaptor =
        // ArgumentCaptor.forClass(SuccessfulPaymentEvent.class);
        User user = new User("Andrey", "Test");
        userDao.addUser(user);

        Mockito.verify(userDao.getEntityManager()).persist(user);

        Assert.assertSame(user.getId(), 1);
        Assert.assertNotSame(user.getId(), 2);
    }

    @Test
    public void testDBConnection() {

        EntityManager entityManager = Mockito.mock(EntityManager.class);
        Query mockedQuery = Mockito.mock(Query.class);
        final String queryString = "SELECT username FROM user WHERE user_id = 1";
        User user = new User("Andrey", "Test");
        user.setId(1);

        Mockito.when(entityManager.createQuery(queryString)).thenReturn(mockedQuery);
        Mockito.when(mockedQuery.getSingleResult()).thenReturn(user);

        Query query = entityManager.createQuery(queryString);
        User databaseReturn = (User) query.getSingleResult();

        assertNotNull(databaseReturn);
        assertEquals(user, databaseReturn);
    }

    private class MockUserAnswer implements Answer<Void> {
        private final Integer id;
        private final String login;
        private final String pass;

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
