package br.com.ab.Trello.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.model.Dashboard;
import br.com.ab.Trello.service.DashboardService;

@WebService
@Stateless
public class DashboardController {

	@Inject
	private DashboardService dashboardService;
	
	public void addDashboard(@WebParam(name="dashboard_name") @XmlElement(required=true, nillable=false) 
	String dashboardName, @WebParam(name="userId") int userId){
		
		if(!dashboardName.equals("") && userId > 0){
			Dashboard dashboard = new Dashboard(dashboardName, userId);
			this.dashboardService.addDashboard(dashboard);
		}
	}
	
	@WebResult(name="dashboardFound")
	public Dashboard findById(@WebParam(name="dashboard_id") @XmlElement(required=true, nillable=false) Integer dashboard_id){
		return this.dashboardService.findById(dashboard_id);
	}

	@WebResult(name="allDashboards")
	public List<Dashboard> getAll(){
		return this.dashboardService.findAllDashboard();
	}
	
}
