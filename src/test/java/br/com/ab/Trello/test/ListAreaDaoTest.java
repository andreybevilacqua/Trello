package br.com.ab.Trello.test;

import javax.persistence.EntityManager;

import br.com.ab.Trello.model.ListArea;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.ab.Trello.dao.ListAreaDao;

public class ListAreaDaoTest {

	private ListAreaDao listAreaDao;
	private EntityManager entityManager;
	
	
	@Before
	public void setup(){
		listAreaDao = new ListAreaDao();
		entityManager = Mockito.mock(EntityManager.class);
		
		listAreaDao.setEntityManager(entityManager);
	}
	
	/*@Test
	public void addListSuccessfullyTest(){
		Mockito.doAnswer(new MockListAnswer(1, "ListArea 1")).when(entityManager).persist(Mockito.any(ListArea.class));
		
		ListArea listArea = new ListArea("listArea 1", 1);
		listArea.setId(1);
		
		listAreaDao.addList(listArea);
		
		Mockito.verify(listAreaDao.getEntityManager()).persist(listArea);
		
		Assert.assertSame(listArea.getId(), 1);
		
	}*/
	
	/*@Test
	public void findListByIdSuccessfullyTest(){
		ListArea listArea = new ListArea("ListArea 1", 1);
		listArea.setId(1);
		
		Mockito.when(entityManager.find(ListArea.class, 1)).thenReturn(listArea);
		
		ListArea testListArea = listAreaDao.findDashboardById(1);

		Assert.assertSame(testListArea.getId(), listArea.getId());
	}*/
	
	/*@Test
	public void findListByTitleSuccessfullyTest(){
		ListArea listArea = new ListArea("First ListArea", 1);
		listArea.setId(1);
		
		Mockito.when(entityManager.find(ListArea.class, "First ListArea")).thenReturn(listArea);
		
		ListArea testListArea = listAreaDao.findByTitle("First ListArea");
		
		Assert.assertSame(testListArea.getTitle(), listArea.getTitle());
	}*/
	
	/*@Test
	public void deleteListByListObjSuccessfullyTest(){
		ListArea listArea = new ListArea("First ListArea", 1);
		listArea.setId(1);
		
		Mockito.when(entityManager.find(ListArea.class, 1)).thenReturn(listArea);
		
		ListArea testListArea = listAreaDao.findDashboardById(1);
		
		if(testListArea != null){
			// Now we should create the mock answer for the delete method.
			//entityManager.remove(testListArea);
		}
		
		// Temporary...
		Assert.assertTrue(true);
		
	}*/
	
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
			ListArea listArea = (ListArea) invocation.getArgument(0);
			listArea.setId(listId);
			listArea.setTitle(title);
			return null;
		}
	}
	
}

