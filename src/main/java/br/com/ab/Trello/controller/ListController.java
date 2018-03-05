package br.com.ab.Trello.controller;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ab.Trello.dao.ListDao;
import br.com.ab.Trello.exception.WSObjectException;
import br.com.ab.Trello.model.List;

@Stateless
public class ListController {

	@Inject
	ListDao listDao;
	
	public ListController() {
		
	}
	
	public List createList(String listName, Integer dashboardId) throws WSObjectException, Exception {
		return new List(listName, dashboardId);
	}
	
	public void addList(List list) throws WSObjectException {
		try {
			listDao.addList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<List> findListByDashboardId(Integer dashboardId){
		return listDao.findListByDashboardId(dashboardId);
	}
}