package br.com.ab.Trello.controller;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ab.Trello.dao.DashboardDao;
import br.com.ab.Trello.exception.WSObjectException;
import br.com.ab.Trello.model.Dashboard;

@Stateless
public class DashboardController {
	
	@Inject
	DashboardDao dashboardDao;
	
	public DashboardController() {
		
	}
	
	public void createDashboard(String dashboardName, int userId) {
		try{
			addDashboard(new Dashboard(dashboardName, userId));
		} catch (WSObjectException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void addDashboard(Dashboard dashboard) throws WSObjectException, Exception {
		dashboardDao.addDashboard(dashboard);
	}
	
}
