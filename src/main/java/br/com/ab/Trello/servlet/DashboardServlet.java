package br.com.ab.Trello.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
import br.com.ab.Trello.model.List;

@WebServlet(name = "DashboardServlet", urlPatterns = { "/dashboard", "/dashboard/*" })
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<List> totalListPerDashboards;
	private ArrayList<Dashboard> dashboards;

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
			
			dispatcher = discoverWhatToDo(uri, req, resp, dispatcher);
			
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

	private RequestDispatcher discoverWhatToDo(String uri, HttpServletRequest req, HttpServletResponse resp,
			RequestDispatcher dispatcher) throws IOException {
		
		if (uri.equals(PathDiscover.getUri("DASHBOARD"))) {
			setListPerDashboard();
			req.setAttribute("dashboards", dashboards);
			
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
			
		} else if (uri.matches((PathDiscover.getUri("DASHBOARD_DELETE")))) {
			try {
				deleteDashboard(PathDiscover.findObjectId(uri));
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(PathDiscover.getUri("DASHBOARD")); // Redirect to dashboards page.
		
		} else if (uri.matches(PathDiscover.getUri("DASHBOARD_DETAIL"))) {
			req.setAttribute("dashboard", dashboardController.findById(PathDiscover.findObjectId(uri)));
			dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("DASHBOARD_DETAIL"));
		}
		
		
		return dispatcher;
	}

	private void addDashboard(String dashboardName, int userId) throws WSObjectException, Exception {
		Dashboard d = dashboardController.createDashboard(dashboardName, userId);
		dashboardController.addDashboard(d);
	}
	
	private void deleteDashboard(int dashboardId) throws Exception {
		dashboardController.deleteDashboard(dashboardId);
	}
	
	private ArrayList<List> totalListsPerDashboard() {
		return listController.totalListsPerDashboard();
	}
	
	private void setListPerDashboard() {
		totalListPerDashboards = totalListsPerDashboard();
		dashboards = (ArrayList<Dashboard>) dashboardController.findAllDashboards();
		
		for (int i = 0; i < dashboards.size(); i++) {
			for(int j = 0; j < totalListPerDashboards.size(); j++) {
				if(totalListPerDashboards.get(j).getDashboardId() == dashboards.get(i).getId()) {
					dashboards.get(i).addList();
				}
			}
		}
		
	}

}
