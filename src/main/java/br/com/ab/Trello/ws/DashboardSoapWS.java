package br.com.ab.Trello.ws;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.dao.DashboardDao;
import br.com.ab.Trello.error.ErrorMessage;
import br.com.ab.Trello.error.WSObjectFault;
import br.com.ab.Trello.exception.WSObjectException;
import br.com.ab.Trello.model.Dashboard;

@WebService
@Stateless
public class DashboardSoapWS {

	@Inject
	DashboardDao dashboardDao;

	@WebMethod(operationName = "addDashboard")
	@WebResult(name = "Dashboard")
	public boolean addDashboard(
			@WebParam(name = "New_Dashboard") @XmlElement(required = true, nillable = false) Dashboard dashboard) throws Exception, WSObjectException {
		try{
			dashboardDao.addDashboard(dashboard);
			return true;
		} catch(Exception e) {
			throw new WSObjectException(new WSObjectFault(ErrorMessage.EMPTY_NULL_PARAMETERS));
		}
	}

	@WebMethod(operationName = "findDashboardById")
	@WebResult(name = "dashboardFound")
	public Dashboard findById(
			@WebParam(name = "dashboardId") @XmlElement(required = true, nillable = false) Integer dashboardId)
			throws Exception, WSObjectException {
		try{
			return this.dashboardDao.findDashboardById(dashboardId);
		} catch(Exception e) {
			throw new WSObjectException(new WSObjectFault(ErrorMessage.ID_NULL_MINOR_ZERO));
		}
	}

	@WebMethod(operationName = "findAllDashboard")
	@WebResult(name = "ListOfDashboards")
	public List<Dashboard> findAllDashboardsFromAUser() {
		return this.dashboardDao.findAllDashboardsFromAUser(1);
	}

}
