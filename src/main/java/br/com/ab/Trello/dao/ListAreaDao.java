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
	
	public void addList(ListArea listArea){
		this.entityManager.persist(listArea);
	}
	
	public ListArea findById(Integer listId){
		ListArea listArea = this.entityManager.find(ListArea.class, listId);
		return listArea;
	}
	
	public ArrayList<ListArea> findAllLists(){
		
		return (ArrayList<ListArea>) this.entityManager.createQuery("SELECT l FROM ListArea l", ListArea.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<ListArea> findListByDashboardId(Integer dashboardId){
		return (ArrayList<ListArea>)entityManager.createQuery("SELECT l FROM ListArea l WHERE l.dashboardId = :dashboardId ")
					 .setParameter("dashboardId", dashboardId)
					 .getResultList();
	}
	
	public void deleteList(ListArea listArea){
		this.entityManager.remove(listArea);
	}
	
	public ListArea findByTitle(String title){
		return this.entityManager.find(ListArea.class, title);
	}
	
	public ArrayList<ListArea> totalListsPerDashboard() {
		
		@SuppressWarnings("unchecked")
		ArrayList<ListArea> myListArea = (ArrayList<ListArea>) entityManager
				.createQuery(" SELECT l " + 
						" FROM Dashboard d, ListArea l " +
						" WHERE l.dashboardId = d.dashboardId " + 
						" ORDER BY d.dashboardId ")
				.getResultList();
		
		return myListArea;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	

}
