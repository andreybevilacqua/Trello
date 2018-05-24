package br.com.ab.Trello.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ab.Trello.model.ListArea;

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
	
	public ListArea findById(Integer listAreaId){
		ListArea listArea = this.entityManager.find(ListArea.class, listAreaId);
		return listArea;
	}

	public void deleteListArea(ListArea listArea){
		this.entityManager.remove(listArea);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ListArea> findAllListAreasFromDashboardByDashboardId(Integer dashboardId){
		return (ArrayList<ListArea>)entityManager.createQuery("SELECT l FROM ListArea l WHERE l.dashboardId = :dashboardId ")
					 .setParameter("dashboardId", dashboardId)
					 .getResultList();
	}

}
