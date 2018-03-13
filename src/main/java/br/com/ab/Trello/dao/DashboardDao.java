package br.com.ab.Trello.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	public void addDashboard(Dashboard dashboard) throws Exception {
			entityManager.persist(dashboard);
	}

	public Dashboard findById(Integer dashboardId) {
		Dashboard dashboard = this.entityManager.find(Dashboard.class, dashboardId);
		return dashboard;
	}

	public List<Dashboard> findAllDashboards() {
		return entityManager.createQuery("SELECT d FROM Dashboard d WHERE d.userId = :user_id", Dashboard.class)
							.setParameter("user_id", 1)
							.getResultList();
	}

}
