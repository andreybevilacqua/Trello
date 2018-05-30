package br.com.ab.Trello.servlet;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

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
	private Dashboard dashboard;
	private ListArea listArea;

	@Inject
	DashboardController dashboardController;

	@Inject
	ListAreaController listAreaController;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		executeURI(req.getRequestURI(), req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	private void executeURI(String uri, HttpServletRequest req, HttpServletResponse resp) {
		RequestDispatcher dispatcher;

		dashboardId = PathDiscover.findObjectId(uri);

		dashboard = findDashboardByDashboardId(dashboardId);

		req.setAttribute("dashboardId", dashboardId);

		try {
			dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("LIST_CREATE"));

			if (uri.matches(PathDiscover.getUri("LIST_CREATE"))) {
				if(req.getParameter("listName") != null) {
					try {
						addNewListArea(req.getParameter("listName"), dashboard);
						dispatcher = req.getRequestDispatcher(PathDiscover.getUri("DASHBOARD_DETAIL"));
					} catch (Exception e) {
						e.printStackTrace();
					}
					resp.sendRedirect(editRedirectURI(PathDiscover.getUri("DASHBOARD_DETAIL")));
				} else {
					dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("LIST_CREATE"));
				}
			} else if (uri.matches(PathDiscover.getUri("LIST_DELETE"))){
				int listAreaId = parseListAreaId(req.getParameter("listAreaId"));

				if(listAreaId > -1){
					listArea = listAreaController.findById(listAreaId);
					deleteListArea(listArea);

					// Encaminha para DASHBOARD_DETAIL.
					dispatcher = req.getRequestDispatcher(PathDiscover.getUri("DASHBOARD_DETAIL"));
					resp.sendRedirect(editRedirectURI(PathDiscover.getUri("DASHBOARD_DETAIL")));
				} else {
					// Retirar o ID do endereço.
					resp.sendRedirect(PathDiscover.getUri("ERROR_PAGE"));
					dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("ERROR_PAGE"));
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

	private void deleteListArea(ListArea listArea) {
		listAreaController.deleteListArea(listArea);
	}

	private void addNewListArea(String listName, Dashboard dashboard) throws WSObjectException, Exception {
		ListArea listArea = listAreaController.createList(listName, dashboard);
		listAreaController.addList(listArea);
	}

	private Dashboard findDashboardByDashboardId(Integer dashboardId){
		return dashboardController.findDashboardByDashboardId(dashboardId);
	}

	private String editRedirectURI(String uri){
		uri = PathDiscover.removeDashboardIdRegexFromURI(uri);
		uri = uri.concat(String.valueOf(dashboardId));
		return uri;
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
