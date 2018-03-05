 package br.com.ab.Trello.ws;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.dao.ListDao;
import br.com.ab.Trello.model.List;

@WebService
@Stateless
public class ListSoapWS {

	@Inject
	ListDao listDao;
	
	public void addList(@WebParam(name = "list") List list) {
		if (!list.equals(null)) {
			this.listDao.addList(list);
		}
	}

	@WebResult(name = "list")
	public ArrayList<List> findByDashboardId(
			@WebParam(name = "dashboardId") @XmlElement(required = true, nillable = false) Integer dashboardId) {
		return this.listDao.findListByDashboardId(dashboardId);
	}

	@WebResult(name = "list")
	public List findById(@WebParam(name = "listId") @XmlElement(required = true, nillable = false) Integer listId) {
		return this.listDao.findById(listId);
	}

	@WebResult(name = "allLists")
	public ArrayList<List> getAll() {
		return this.listDao.findAllLists();
	}

	public void delete(@WebParam(name = "list") @XmlElement(required = true, nillable = false) List list) {
		this.listDao.deleteList(list);
	}
}
