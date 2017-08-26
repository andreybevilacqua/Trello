package br.com.ab.Trello.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ab.Trello.dao.DashboardDao;
import br.com.ab.Trello.model.Dashboard;

@Stateless
public class DashboardService {

	@Inject
	DashboardDao dashboardDao;
	
	public void addDashboard(Dashboard dashboard){
		dashboardDao.addDashborard(dashboard);
	}
	
	public Dashboard findDashboardById(Integer dashboardId){
		return dashboardDao.findDashboardById(dashboardId);
	}
	
	public List<Dashboard> findAllDashboard(){
		return dashboardDao.findAllDashboard();
		
	}
}
