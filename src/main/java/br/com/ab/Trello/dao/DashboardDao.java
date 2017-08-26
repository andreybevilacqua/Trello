package br.com.ab.Trello.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;

import br.com.ab.Trello.model.Dashboard;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DashboardDao {
	
	private EntityManager entityManager;
	
	public void addDashborard(Dashboard dashboard){
		entityManager.persist(dashboard);
	}
	
	public Dashboard findDashboardById(Integer dashboardId){
		Dashboard dashboard = this.entityManager.find(Dashboard.class, dashboardId);
		return dashboard;
	}
	
	public List<Dashboard> findAllDashboard(){
		return entityManager.createQuery("SELECT d FROM dashboard d", Dashboard.class).getResultList();
		
	}

}
