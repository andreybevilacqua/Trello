package br.com.ab.Trello.dao;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ab.Trello.model.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ListDao {
	
	private EntityManager entityManager;
	
	public void addList(List list){
		entityManager.persist(list);
	}
	
	public List findListById(Integer list_id){
		List list = entityManager.find(List.class, list_id);
		return list;
	}
	
	public ArrayList<List> findAllLists(){
		
		return (ArrayList<List>) entityManager.createQuery("SELECT l FROM list l", List.class).getResultList();
	}
	
	public List findListByDashboardId(Integer dashboard_id){
		String query = "SELECT l FROM list l, dashboard d WHERE l.dashboard_id = :pDashboard_id";
		
		TypedQuery<List> typedQuery = (TypedQuery<List>) entityManager.createQuery(query);
		typedQuery.setParameter("pDashboard_id", dashboard_id);
		
		List list = (List) typedQuery.getSingleResult();
		
		return list;
	}
	
	public void deleteList(List list){
		entityManager.remove(list);
	}

}
