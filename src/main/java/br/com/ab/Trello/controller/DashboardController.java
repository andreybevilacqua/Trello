package br.com.ab.Trello.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.error.ErrorMessage;
import br.com.ab.Trello.error.WSObjectFault;
import br.com.ab.Trello.exception.WSObjectException;
import br.com.ab.Trello.model.Dashboard;
import br.com.ab.Trello.service.DashboardService;

@WebService
@Stateless
public class DashboardController {

	@Inject
	private DashboardService dashboardService;

	@WebMethod(operationName = "addDashboard")
	@WebResult(name = "Dashboard")
	public Dashboard addDashboard(
			@WebParam(name = "New_Dashboard") @XmlElement(required = true, nillable = false) Dashboard dashboard)
			throws WSObjectException {
		if (dashboardService.validateDashboard(dashboard)) {
			return dashboardService.addDashboard(dashboard);
		} else {
			throw new WSObjectException(new WSObjectFault(ErrorMessage.EMPTY_NULL_PARAMETERS));
		}
	}

	@WebMethod(operationName = "findById")
	@WebResult(name = "dashboardFound")
	public Dashboard findById(
			@WebParam(name = "dashboardId") @XmlElement(required = true, nillable = false) Integer dashboardId)
			throws WSObjectException {
		if (dashboardService.validateDashboardId(dashboardId)) {
			return this.dashboardService.findDashboardById(dashboardId);
		} else {
			throw new WSObjectException(new WSObjectFault(ErrorMessage.ID_NULL_MINOR_ZERO));
		}
	}

	@WebMethod(operationName="findAllDashboard")
	@WebResult(name="ListOfDashboards")
	public List<Dashboard> findAllDashboard() {
		return this.dashboardService.findAllDashboard();
	}

}
