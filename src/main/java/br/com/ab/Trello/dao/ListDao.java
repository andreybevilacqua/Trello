package br.com.ab.Trello.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.ab.Trello.model.Dashboard;
import br.com.ab.Trello.model.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ListDao {
	
	@PersistenceContext(unitName = "Trello")
	private EntityManager entityManager;
	
	public void addList(List list){
		this.entityManager.persist(list);
	}
	
	public List findById(Integer listId){
		List list = this.entityManager.find(List.class, listId);
		return list;
	}
	
	public ArrayList<List> findAllLists(){
		
		return (ArrayList<List>) this.entityManager.createQuery("SELECT l FROM List l", List.class).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<List> findListByDashboardId(Integer dashboardId){
		return (ArrayList<List>)entityManager.createQuery("SELECT l FROM List l WHERE l.dashboardId = :dashboardId ")
					 .setParameter("dashboardId", dashboardId)
					 .getResultList();
	}
	
	public void deleteList(List list){
		this.entityManager.remove(list);
	}
	
	public List findByTitle(String title){
		return this.entityManager.find(List.class, title);
	}
	
	public ArrayList<List> totalListsPerDashboard() {	
		
		@SuppressWarnings("unchecked")
		ArrayList<List> myList = (ArrayList<List>) entityManager
				.createQuery(" SELECT l " + 
						" FROM Dashboard d, List l " + 
						" WHERE l.dashboardId = d.dashboardId " + 
						" ORDER BY d.dashboardId ")
				.getResultList();
		
		return myList;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	

}
