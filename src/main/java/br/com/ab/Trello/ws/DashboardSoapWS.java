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
	
	public Dashboard addDashboard(Dashboard dashboard){
		try {
			this.dashboardDao.addDashborard(dashboard);
			return dashboard;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Dashboard findDashboardById(Integer dashboardId){
		try{
			return this.dashboardDao.findById(dashboardId);
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Dashboard> findAllDashboard(){
		try{
			return this.dashboardDao.findAllDashboard();
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean validateDashboard(Dashboard dashboard) {
		boolean result = false;
		if (!dashboard.equals(null)){
			if(!dashboard.getTitle().equals(null) && !(dashboard.getUserId() < 0)) {
				if (!dashboard.getTitle().equals("") && !dashboard.getTitle().equals("?")){
					result = true;
				}
			}
		}
		return result;
	}

	public boolean validateDashboardId(Integer dashboardId) {
		boolean result = false;
		if(dashboardId != null && dashboardId >= 0){
			result = true;
		}
		return result;
	}
}
