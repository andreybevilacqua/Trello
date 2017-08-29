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
	
	public List findById(Integer list_id){
		return this.listDao.findById(list_id);
	}
	
	public ArrayList<List> findAllLists(){
		
		return (ArrayList<List>) this.listDao.findAllLists();
	}
	
	public List findByDashboardId(Integer dashboard_id){
		return this.listDao.findListByDashboardId(dashboard_id);
	}
	
	public void deleteList(List list){
		this.listDao.deleteList(list);
	}
}
