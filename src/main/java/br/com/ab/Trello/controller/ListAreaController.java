package br.com.ab.Trello.controller;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ab.Trello.dao.ListAreaDao;
import br.com.ab.Trello.exception.WSObjectException;
import br.com.ab.Trello.model.Dashboard;
import br.com.ab.Trello.model.ListArea;

@Stateless
public class ListAreaController {

	@Inject
	ListAreaDao listAreaDao;
	
	public ListAreaController() {
	}
	
	public ListArea createList(String listName, Dashboard dashboard) throws WSObjectException, Exception {
		return new ListArea(listName, dashboard);
	}
	
	public void addList(ListArea listArea) throws WSObjectException {
		try {
			listAreaDao.addList(listArea);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ListArea> findAllLists(){
		return listAreaDao.findAllLists();
	}
	
	public ArrayList<ListArea> findListByDashboardId(Integer dashboardId){
		return listAreaDao.findListByDashboardId(dashboardId);
	}

	public ArrayList<ListArea> totalListsPerDashboard() {
		return listAreaDao.totalListsPerDashboard();
	}
}