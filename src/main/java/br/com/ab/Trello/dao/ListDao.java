package br.com.ab.Trello.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		
		return (ArrayList<List>) this.entityManager.createQuery("SELECT l FROM list l", List.class).getResultList();
	}
	
	public List findListByDashboardId(Integer dashboardId){
		String query = "SELECT l FROM list l, dashboard d WHERE l.dashboard_id = :pDashboard_id";
		
		TypedQuery<List> typedQuery = (TypedQuery<List>) this.entityManager.createQuery(query);
		typedQuery.setParameter("pDashboard_id", dashboardId);
		
		List list = (List) typedQuery.getSingleResult();
		
		return list;
	}
	
	public void deleteList(List list){
		this.entityManager.remove(list);
	}
	
	public List findByTitle(String title){
		return this.entityManager.find(List.class, title);
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	

}
