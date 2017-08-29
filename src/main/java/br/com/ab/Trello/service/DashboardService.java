package br.com.ab.Trello.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletException;

import br.com.ab.Trello.dao.DashboardDao;
import br.com.ab.Trello.model.Dashboard;

@Stateless
public class DashboardService {

	@Inject
	DashboardDao dashboardDao;
	
	public void addDashboard(Dashboard dashboard){
		try {
			this.dashboardDao.addDashborard(dashboard);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	public Dashboard findById(Integer dashboardId){
		return this.dashboardDao.findById(dashboardId);
	}
	
	public List<Dashboard> findAllDashboard(){
		return this.dashboardDao.findAllDashboard();
		
	}
}
