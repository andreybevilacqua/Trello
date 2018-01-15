package br.com.ab.Trello.controller;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import br.com.ab.Trello.model.List;
import br.com.ab.Trello.service.ListService;

@WebService
@Stateless
public class ListController {

	@Inject
	private ListService listService;

	public ListController() {
		this.listService = new ListService();
	}

	public void addList(@WebParam(name = "list") List list) {
		if (!list.equals(null)) {
			this.listService.addList(list);
		}
	}

	@WebResult(name = "list")
	public List findByDashboardId(
			@WebParam(name = "dashboardId") @XmlElement(required = true, nillable = false) Integer dashboardId) {
		return this.listService.findByDashboardId(dashboardId);
	}

	@WebResult(name = "list")
	public List findById(@WebParam(name = "listId") @XmlElement(required = true, nillable = false) Integer listId) {
		return this.listService.findById(listId);
	}

	@WebResult(name = "allLists")
	public ArrayList<List> getAll() {
		return this.listService.findAllLists();
	}

	public void delete(@WebParam(name = "list") @XmlElement(required = true, nillable = false) List list) {
		this.listService.deleteList(list);
	}
}