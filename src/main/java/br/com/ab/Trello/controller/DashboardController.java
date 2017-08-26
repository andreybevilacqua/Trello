package br.com.ab.Trello.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.ab.Trello.model.Dashboard;
import br.com.ab.Trello.service.DashboardService;

@Path("/Dashboard")
public class DashboardController {

	private DashboardService dashboardService;
	
	public DashboardController(){
		dashboardService = new DashboardService();
	}
	
	@PUT
	@Path("/addDashboard")
	@Consumes({MediaType.APPLICATION_JSON})
	public void addDashboard(Dashboard dashboard){
		if(!dashboard.equals(null)){
			dashboardService.addDashboard(dashboard);			
		}
		
	}
	
	@POST
	@Path("/findDashboardById")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Dashboard findDashboardById(Integer dashboard_id){
		
		Dashboard dashboard = null;
		
		if(!dashboard_id.equals(null)){
			dashboard = dashboardService.findDashboardById(dashboard_id);
		}
		
		return dashboard;
	}
}
