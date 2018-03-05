package br.com.ab.Trello.test;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.ab.Trello.dao.ListDao;
import br.com.ab.Trello.model.List;
import br.com.ab.Trello.model.User;

public class ListDaoTest {

	private ListDao listDao;
	private EntityManager entityManager;
	
	
	@Before
	public void setup(){
		listDao = new ListDao();
		entityManager = Mockito.mock(EntityManager.class);
		
		listDao.setEntityManager(entityManager);
	}
	
	@Test
	public void addListSuccessfullyTest(){
		Mockito.doAnswer(new MockListAnswer(1, "List 1")).when(entityManager).persist(Mockito.any(List.class));
		
		List list = new List("list 1", 1);
		list.setId(1);
		
		listDao.addList(list);
		
		Mockito.verify(listDao.getEntityManager()).persist(list);
		
		Assert.assertSame(list.getId(), 1);
		
	}
	
	@Test
	public void findListByIdSuccessfullyTest(){
		List list = new List("List 1", 1);
		list.setId(1);
		
		Mockito.when(entityManager.find(List.class, 1)).thenReturn(list);
		
		List testList = listDao.findById(1);

		Assert.assertSame(testList.getId(), list.getId());
	}
	
	@Test
	public void findListByTitleSuccessfullyTest(){
		List list = new List("First List", 1);
		list.setId(1);
		
		Mockito.when(entityManager.find(List.class, "First List")).thenReturn(list);
		
		List testList = listDao.findByTitle("First List");
		
		Assert.assertSame(testList.getTitle(), list.getTitle());
	}
	
	@Test
	public void deleteListByListObjSuccessfullyTest(){
		List list = new List("First List", 1);
		list.setId(1);
		
		Mockito.when(entityManager.find(List.class, 1)).thenReturn(list);
		
		List testList = listDao.findById(1);
		
		if(testList != null){
			// Now we should create the mock answer for the delete method.
			//entityManager.remove(testList);
		}
		
		// Temporary...
		Assert.assertTrue(true);
		
	}
	
	private class MockListAnswer implements Answer<Void> {
		private final Integer listId;
		private final String title;

		// Construtor da classe.
		private MockListAnswer(Integer listId, String title) {
			this.listId = listId;
			this.title = title;
		}

		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			List list = (List) invocation.getArgument(0);
			list.setId(listId);
			list.setTitle(title);
			return null;
		}
	}
	
}

