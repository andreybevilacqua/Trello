package br.com.ab.Trello.dao;

import br.com.ab.Trello.model.ListArea;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ListAreaDao {
	
	@PersistenceContext(unitName = "Trello")
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	public void addList(ListArea listArea){
		this.entityManager.persist(listArea);
	}
	
	public ListArea findListAreaById(Integer listAreaId){
		return this.entityManager.find(ListArea.class, listAreaId);
	}

	public void deleteListArea(int listAreaId){
		ListArea listArea = this.entityManager.find(ListArea.class, listAreaId);
		this.entityManager.remove(listArea);
	}

	public void editListAreaTitle(String listAreaName, int listAreaId){
		ListArea listArea = this.entityManager.find(ListArea.class, listAreaId);
		listArea.setTitle(listAreaName);
		this.entityManager.merge(listArea);
	}

}
