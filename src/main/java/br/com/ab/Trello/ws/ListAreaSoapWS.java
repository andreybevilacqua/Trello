 package br.com.ab.Trello.ws;

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
	public ListArea findById(@WebParam(name = "listId") @XmlElement(required = true, nillable = false) Integer listId) {
		return this.listAreaDao.findListAreaById(listId);
	}

	public void delete(@WebParam(name = "listArea") @XmlElement(required = true, nillable = false) int listAreaId) {
		this.listAreaDao.deleteListArea(listAreaId);
	}
}
