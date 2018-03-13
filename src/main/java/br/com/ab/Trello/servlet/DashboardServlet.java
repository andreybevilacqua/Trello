package br.com.ab.Trello.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ab.Trello.controller.DashboardController;
import br.com.ab.Trello.controller.ListController;
import br.com.ab.Trello.exception.WSObjectException;
import br.com.ab.Trello.model.Dashboard;

@WebServlet(name = "DashboardServlet", urlPatterns = { "/dashboard", "/dashboard/*" })
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	DashboardController dashboardController;
	
	@Inject
	ListController listController;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		executeURI(PathDiscover.discoverURI(req.getRequestURI()), req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private void executeURI(String uri, HttpServletRequest req, HttpServletResponse resp) {
		RequestDispatcher dispatcher;
		
		try {
			dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("DASHBOARD"));

			if (uri.equals(PathDiscover.getUri("DASHBOARD"))) {
				req.setAttribute("dashboards", dashboardController.findAllDashboards());
				req.setAttribute("totalListsPerDashboard", totalListsPerDashboard(1));
				dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("DASHBOARD"));
				
			} else if (uri.equals(PathDiscover.getUri("DASHBOARD_CREATE"))) {
				if(req.getParameter("dashboardName") != null) {
					try {
						addDashboard(req.getParameter("dashboardName"), Integer.parseInt(req.getParameter("userId")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					resp.sendRedirect(PathDiscover.getUri("DASHBOARD")); // Redirect to dashboards page.
				} else {
					dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("DASHBOARD_CREATE"));
				}
				
			}
			
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
	
	private void addDashboard(String dashboardName, int userId) throws WSObjectException, Exception {
		Dashboard d = dashboardController.createDashboard(dashboardName, userId);
		dashboardController.addDashboard(d);
	}
	
	private int totalListsPerDashboard(Integer dashboardId) {
		return listController.totalListsPerDashboard(dashboardId);
	}

}
