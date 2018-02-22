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

@WebServlet(name = "DashboardServlet", urlPatterns = { "/dashboard", "/dashboard/*" })
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	DashboardController dashboardController;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		URIDiscover uri = new URIDiscover();
		executeURI(uri.discoverURI(req.getRequestURI()), req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	public void executeURI(String uri, HttpServletRequest req, HttpServletResponse resp) {
		RequestDispatcher dispatcher = req.getRequestDispatcher(JSPPath.DASHBOARD.toString());

		if (uri.equals(URI.DASHBOARD_URI.toString())) {
			dispatcher = req.getRequestDispatcher(JSPPath.DASHBOARD.toString());
			
		} else if (uri.equals(URI.DASHBOARD_CREATE_URI.toString())) {
			dispatcher = req.getRequestDispatcher(JSPPath.DASHBOARD_CREATE.toString());
		}

		try {
			dispatcher.forward(req, resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private int parseUserId(String userId) throws NumberFormatException {
		return Integer.parseInt(userId);
	}

	/*private boolean emptyParameters(HttpServletRequest req) {
		if (req.getParameter("dashboardName").isEmpty() || req.getParameter("userId").isEmpty()) {
			return true;
		}
		return false;
	}*/

}
