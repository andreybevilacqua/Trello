package br.com.ab.Trello.service;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.ab.Trello.dao.ListDao;
import br.com.ab.Trello.model.List;

@Stateless
public class ListService {

	@Inject
	ListDao listDao;
	
	public void addList(List list){
		listDao.addList(list);
	}
	
	public List findListById(Integer list_id){
		return listDao.findListById(list_id);
	}
	
	public ArrayList<List> findAllLists(){
		
		return (ArrayList<List>) listDao.findAllLists();
	}
	
	public List findListByDashboardId(Integer dashboard_id){
		return listDao.findListByDashboardId(dashboard_id);
	}
	
	public void deleteList(List list){
		listDao.deleteList(list);
	}
}
