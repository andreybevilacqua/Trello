package br.com.ab.Trello.test;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.ab.Trello.dao.DashboardDao;
import br.com.ab.Trello.model.Dashboard;
import org.junit.Assert;

public class DashboardDaoTest {

	private DashboardDao dashboardDao;
	private EntityManager entityManager;
	
	@Before
	public void setup(){
		dashboardDao = new DashboardDao();
		entityManager = Mockito.mock(EntityManager.class);
		
		dashboardDao.setEntityManager(entityManager);
	}
	
	/*@SuppressWarnings("deprecation")
	@Test
	public void addDashboardSucessfullyTest() throws Exception{
		
		Mockito.doAnswer(new MockDashboardAnswer(1, "Test Dashboard", 1)).when(entityManager).persist(Mockito.any(Dashboard.class));
		
		Dashboard dashboard = new Dashboard(1, "Test Dash", 1);
		dashboardDao.addDashboard(dashboard);
		
		Mockito.verify(dashboardDao.getEntityManager()).persist(dashboard);
		
		Assert.assertSame(dashboard.getUserId(), 1);
		Assert.assertSame(dashboard.getTitle(), "Test Dashboard");
	}*/
	
	/*@SuppressWarnings("deprecation")
	@Test
	public void findDashboardByIdTest(){
		Dashboard dashboard = new Dashboard("Dashboard 1", 1);
		dashboard.setId(1);
		
		Mockito.when(entityManager.find(Dashboard.class,  dashboard.getId())).thenReturn(dashboard);
		
		Dashboard testDashboard = dashboardDao.findDashboardById(1);
		
		Assert.assertEquals(dashboard.getId(), testDashboard.getId());
		Assert.assertEquals(dashboard.getUserId(), testDashboard.getUserId());
	}*/
	
	private class MockDashboardAnswer implements Answer<Void>{

		private int dashboardId;
		private String dashboardName;
		private int userId;
		
		private MockDashboardAnswer(int dashboardId, String dashboardName, int userId){
			this.dashboardId = dashboardId;
			this.dashboardName = dashboardName;
			this.userId = userId;
		}
		
		@Override
		public Void answer(InvocationOnMock invocation) {
			Dashboard dashboard = invocation.getArgument(0);
			dashboard.setId(dashboardId);
			dashboard.setTitle(dashboardName);
			return null;
		}
		
		
	}
	
}
