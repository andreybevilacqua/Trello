package br.com.ab.Trello.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ab.Trello.controller.ApplicationUserController;
import br.com.ab.Trello.controller.DashboardController;
import br.com.ab.Trello.exception.WSObjectException;
import br.com.ab.Trello.model.ApplicationUser;
import br.com.ab.Trello.model.Dashboard;

@WebServlet(name = "DashboardServlet", urlPatterns = { "/dashboard", "/dashboard/*" })
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ArrayList<Dashboard> dashboards;

	@Inject
	ApplicationUserController applicationUserController;

	@Inject
	DashboardController dashboardController;
	
	//@Inject
	//ListAreaController listAreaController;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//executeURI(PathDiscover.discoverURI(req.getRequestURI()), req, resp);
		executeURI(req.getRequestURI(), req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private void executeURI(String uri, HttpServletRequest req, HttpServletResponse resp) {
		RequestDispatcher dispatcher;

		try {
			dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("DASHBOARD"));
			
			dispatcher = discoverWhichURIShouldForwad(uri, req, resp, dispatcher);
			
			dispatcher.forward(req, resp);
			
		} catch (NullPointerException npex) {
			dispatcher = req.getRequestDispatcher("/");
			
			try {
				dispatcher.forward(req, resp);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private RequestDispatcher discoverWhichURIShouldForwad(String uri, HttpServletRequest req, HttpServletResponse resp,
														   RequestDispatcher dispatcher) throws IOException {
		
		if (uri.equals(PathDiscover.getUri("DASHBOARD"))) {
			dashboards = (ArrayList<Dashboard>) findAllDashboardsFromAUser(getLoggedUser().getId());
			req.setAttribute("dashboards", dashboards);
			
			dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("DASHBOARD"));
			
		} else if (uri.equals(PathDiscover.getUri("DASHBOARD_CREATE"))) {
			if(req.getParameter("dashboardName") != null) {
				try {
					addNewDashboard(req.getParameter("dashboardName"), getLoggedUser());
				} catch (Exception e) {
					e.printStackTrace();
				}
				resp.sendRedirect(PathDiscover.getUri("DASHBOARD")); // Redirect to dashboards page.
			} else {
				dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("DASHBOARD_CREATE"));
			}
			
		} else if (uri.matches((PathDiscover.getUri("DASHBOARD_DELETE")))) {
			try {
				deleteDashboard(PathDiscover.findObjectId(uri));
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(PathDiscover.getUri("DASHBOARD")); // Redirect to dashboards page.
		
		} else if (uri.matches(PathDiscover.getUri("DASHBOARD_DETAIL"))) {
			Dashboard d = dashboardController.findDashboardById(PathDiscover.findObjectId(uri));
			req.setAttribute("dashboard", d);
			req.setAttribute("dashboardId", d.getId()); //
			dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("DASHBOARD_DETAIL"));
		}

		return dispatcher;
	}

	private ApplicationUser getLoggedUser() {
		return applicationUserController.findUserById(1);
	}

	private void addNewDashboard(String dashboardName, ApplicationUser user) throws WSObjectException, Exception {
		Dashboard d = dashboardController.createDashboard(dashboardName, user);
		dashboardController.addDashboard(d);
	}
	
	private void deleteDashboard(int dashboardId) throws Exception {
		dashboardController.deleteDashboard(dashboardId);
	}

	private List<Dashboard> findAllDashboardsFromAUser(Integer userId){
		return dashboardController.findAllDashboardsFromAnUserId(userId);
	}

}
