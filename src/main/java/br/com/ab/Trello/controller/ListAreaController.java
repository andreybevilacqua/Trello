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
	
	public ListArea createListArea(String listName, Dashboard dashboard) throws WSObjectException, Exception {
		return new ListArea(listName, dashboard);
	}

	public ListArea findListAreaById(Integer listAreaId){
		return listAreaDao.findListAreaById(listAreaId);
	}

	public void deleteListArea(int listAreaId){ listAreaDao.deleteListArea(listAreaId); }

	public void addList(ListArea listArea) throws WSObjectException {
		try {
			listAreaDao.addList(listArea);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editListAreaTitle(String listAreaName, int listAreaId){
		listAreaDao.editListAreaTitle(listAreaName, listAreaId);
	}
	
	/*public ArrayList<ListArea> findAllListAreasFromDashboardByDashboardId(Integer dashboardId){
		return listAreaDao.findAllListAreasFromDashboardByDashboardId(dashboardId);
	}*/

}