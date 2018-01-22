package br.com.ab.Trello.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;

import br.com.ab.Trello.model.Dashboard;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DashboardDao {

	@PersistenceContext(unitName = "Trello")
	private EntityManager entityManager;
	
	public void setEntityManager(EntityManager entityManager){
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager(){
		return entityManager;
	}
	
	public void addDashborard(Dashboard dashboard) throws Exception {
		try{
			entityManager.persist(dashboard);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public Dashboard findById(Integer dashboardId) {
		Dashboard dashboard = this.entityManager.find(Dashboard.class, dashboardId);
		return dashboard;
	}

	public List<Dashboard> findAllDashboard() {
		return entityManager.createQuery("SELECT d FROM Dashboard d", Dashboard.class).getResultList();

	}

}
