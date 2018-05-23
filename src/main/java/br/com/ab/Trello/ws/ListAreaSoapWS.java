 package br.com.ab.Trello.ws;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.dao.ListAreaDao;
import br.com.ab.Trello.model.ListArea;

@WebService
@Stateless
public class ListAreaSoapWS {

	@Inject
	ListAreaDao listAreaDao;
	
	public void addList(@WebParam(name = "listArea") ListArea listArea) {
		if (!listArea.equals(null)) {
			this.listAreaDao.addList(listArea);
		}
	}

	@WebResult(name = "list")
	public ArrayList<ListArea> findByDashboardId(
			@WebParam(name = "dashboardId") @XmlElement(required = true, nillable = false) Integer dashboardId) {
		return this.listAreaDao.findListByDashboardId(dashboardId);
	}

	@WebResult(name = "list")
	public ListArea findById(@WebParam(name = "listId") @XmlElement(required = true, nillable = false) Integer listId) {
		return this.listAreaDao.findById(listId);
	}

	@WebResult(name = "allLists")
	public ArrayList<ListArea> getAll() {
		return this.listAreaDao.findAllLists();
	}

	public void delete(@WebParam(name = "listArea") @XmlElement(required = true, nillable = false) ListArea listArea) {
		this.listAreaDao.deleteList(listArea);
	}
}
