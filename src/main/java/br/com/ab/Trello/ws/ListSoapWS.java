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
		if(this.listDao.findByTitle(list.getTitle()).equals(null)){
			this.listDao.addList(list);
		}
	}
	
	public List findById(Integer listId){
		return this.listDao.findById(listId);
	}
	
	public ArrayList<List> findAllLists(){
		
		return (ArrayList<List>) this.listDao.findAllLists();
	}
	
	public List findByDashboardId(Integer dashboardId){
		return this.listDao.findListByDashboardId(dashboardId);
	}
	
	public void deleteList(List list){
		this.listDao.deleteList(list);
	}
}
