package br.com.ab.Trello.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ab.Trello.dao.DashboardDao;
import br.com.ab.Trello.model.Dashboard;
import br.com.ab.Trello.model.ApplicationUser;

@Stateless
public class DashboardController {
	
	@Inject
	private DashboardDao dashboardDao;
	
	public DashboardController() { }
	
	public Dashboard createDashboard(String dashboardName, ApplicationUser applicationUser) {
		return new Dashboard(dashboardName, applicationUser);
	}
	
	public void addDashboard(Dashboard dashboard) {
		try{
			dashboardDao.addDashboard(dashboard);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Dashboard findDashboardById(Integer dashboardId) {
		return dashboardDao.findDashboardById(dashboardId);
	}
	
	public List<Dashboard> findAllDashboardsFromAnUserId(Integer userId) {
		return dashboardDao.findAllDashboardsFromAUser(userId);
	}
	
	public void deleteDashboard(int dashboardId) throws Exception {
		dashboardDao.deleteDashboard(dashboardId);
	}
	
}
