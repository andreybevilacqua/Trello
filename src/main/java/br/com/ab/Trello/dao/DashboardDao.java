package br.com.ab.Trello.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	public List<Dashboard> findAllDashboardsFromAUser(Integer userId){
		Query query = entityManager.createQuery
									("SELECT d FROM Dashboard d WHERE applicationuser_userid = :user_id", Dashboard.class)
									.setParameter("user_id", userId);

		List<Dashboard> dashboardList = query.getResultList();

		return dashboardList;
	}
	
	public void deleteDashboard(int dashboardId) throws Exception {
		entityManager.createQuery("DELETE FROM Dashboard d WHERE d.dashboardId = :dashboardId")
					.setParameter("dashboardId", dashboardId)
					.executeUpdate();
	}

}
