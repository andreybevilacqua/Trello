package br.com.ab.Trello.dao;

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
	
	public void addDashboard(Dashboard dashboard) {
			try{
				entityManager.persist(dashboard);
			} catch (Exception e){
				e.printStackTrace();
				throw e;
			}
	}

	public Dashboard findDashboardById(Integer dashboardId) {
		return this.entityManager.find(Dashboard.class, dashboardId);
	}

	public List<Dashboard> findAllDashboardsFromAUser(Integer userId){
		Query query = entityManager.createQuery
									("SELECT d FROM Dashboard d WHERE applicationuser_userid = :user_id", Dashboard.class)
									.setParameter("user_id", userId);

		return (List<Dashboard>) query.getResultList();
	}
	
	public void deleteDashboard(int dashboardId) {
		Dashboard dashboard = this.entityManager.find(Dashboard.class, dashboardId);
		this.entityManager.remove(dashboard);
		/*entityManager.createQuery("DELETE FROM Dashboard d WHERE d.dashboardId = :dashboardId")
					.setParameter("dashboardId", dashboardId)
					.executeUpdate();*/
	}

}
