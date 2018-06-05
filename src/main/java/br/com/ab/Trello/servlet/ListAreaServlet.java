package br.com.ab.Trello.servlet;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ab.Trello.controller.DashboardController;
import br.com.ab.Trello.controller.ListAreaController;
import br.com.ab.Trello.exception.WSObjectException;
import br.com.ab.Trello.model.Dashboard;
import br.com.ab.Trello.model.ListArea;

@WebServlet(name="ListAreaServlet", urlPatterns = {"/list", "/list/*"})
public class ListAreaServlet extends HttpServlet implements Servlet, ServletConfig, Serializable {

	private static final long serialVersionUID = 1L;

	private Integer dashboardId = 0;
	private int listAreaId;

	private Dashboard dashboard;

	@Inject
	private DashboardController dashboardController;

	@Inject
	private ListAreaController listAreaController;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		executeURI(req.getRequestURI(), req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	private void prepareDashboardForListAreaServlet(String stringDashboardId, HttpServletRequest req){
		this.dashboardId = Integer.parseInt(req.getParameter("dashboardId"));
		this.dashboard = findDashboardByDashboardId(dashboardId);
		req.setAttribute("dashboardId", dashboardId);
	}
	
	private void executeURI(String uri, HttpServletRequest req, HttpServletResponse resp) {

		RequestDispatcher dispatcher;
/*
        dashboardId = Integer.parseInt(req.getParameter("dashboardId"));

		dashboard = findDashboardByDashboardId(dashboardId);

		req.setAttribute("dashboardId", dashboardId);
*/
		try {
			dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("LIST_CREATE"));

			if (uri.matches(PathDiscover.getUri("LIST_CREATE"))) {

				String listAreaName = req.getParameter("listName");

				if(listAreaName != null) {
					try {
						addNewListArea(req.getParameter("listName"), this.dashboard);
						dispatcher = req.getRequestDispatcher(PathDiscover.getUri("DASHBOARD_DETAIL"));
					} catch (Exception e) {
						e.printStackTrace();
					}
					resp.sendRedirect(PathDiscover.editRedirectURI(PathDiscover.getUri("DASHBOARD_DETAIL"), this.dashboardId));
				} else {
					prepareDashboardForListAreaServlet(req.getParameter("dashboardId"), req);
					dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("LIST_CREATE"));
				}
			} else if (uri.matches(PathDiscover.getUri("LIST_DELETE"))){
				listAreaId = parseListAreaId(req.getParameter("listAreaId"));

				if(listAreaId > -1){
					deleteListArea(listAreaId);
					prepareDashboardForListAreaServlet(req.getParameter("dashboardId"), req);
					dispatcher = req.getRequestDispatcher(PathDiscover.getUri("DASHBOARD_DETAIL"));
					resp.sendRedirect(PathDiscover.editRedirectURI(PathDiscover.getUri("DASHBOARD_DETAIL"), this.dashboardId)); // Redirect do Dashboard Servlet
				} else {
					// Retirar o ID do endereço.
					dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("ERROR_PAGE"));
				}
			} else if(uri.matches(PathDiscover.getUri("LIST_EDIT"))){

				String listAreaName = req.getParameter("listName");
				listAreaId = parseListAreaId(req.getParameter("listAreaId"));

				if(listAreaName != null){
					try{
						listAreaController.editListAreaTitle(listAreaName, listAreaId);
					} catch (Exception e){
						e.printStackTrace();
					}
					resp.sendRedirect(PathDiscover.editRedirectURI(PathDiscover.getUri("DASHBOARD_DETAIL"), this.dashboardId));
				} else{
					ListArea listArea = findListAreaById(listAreaId);
					prepareDashboardForListAreaServlet(req.getParameter("dashboardId"), req);
					req.setAttribute("listArea", listArea);

					dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("LIST_EDIT"));
				}
			} else if (uri.matches(PathDiscover.getUri("LIST_DETAIL"))){
				listAreaId = parseListAreaId(req.getParameter("listAreaId"));
				ListArea listArea = listAreaController.findListAreaById(listAreaId);
				req.setAttribute("listArea", listArea);
				dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("LIST_DETAIL"));
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

	private ListArea findListAreaById(int listAreaId){
		return listAreaController.findListAreaById(listAreaId);
    }

	private void deleteListArea(int listAreaId) {
		listAreaController.deleteListArea(listAreaId);
	}

	private void addNewListArea(String listName, Dashboard dashboard) throws Exception {
		ListArea listArea = listAreaController.createListArea(listName, dashboard);
		listAreaController.addList(listArea);
	}

	private Dashboard findDashboardByDashboardId(Integer dashboardId){
		return dashboardController.findDashboardById(dashboardId);
	}

	private int parseListAreaId(String stringListAreaId){
		try{
			return Integer.parseInt(stringListAreaId);
		} catch (NumberFormatException e){
			return -1;
		} catch (Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
}
