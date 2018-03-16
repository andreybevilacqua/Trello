package br.com.ab.Trello.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	public Dashboard createDashboard(String dashboardName, int userId) {
		return new Dashboard(dashboardName, userId);
	}
	
	public void addDashboard(Dashboard dashboard) throws WSObjectException, Exception {
		try{
			dashboardDao.addDashboard(dashboard);
		} catch (WSObjectException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Dashboard> findAllDashboards() {
		return dashboardDao.findAllDashboards();
	}
	
	public void deleteDashboard(int dashboardId) throws Exception {
		dashboardDao.deleteDashboard(dashboardId);
	}
	
}
