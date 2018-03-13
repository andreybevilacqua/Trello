package br.com.ab.Trello.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ab.Trello.controller.ListController;
import br.com.ab.Trello.exception.WSObjectException;
import br.com.ab.Trello.model.List;

@WebServlet(name="ListServlet", urlPatterns = {"/list", "/list/*"})
public class ListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
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
			dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("LIST"));
			
			if(uri.equals(PathDiscover.getUri("LIST"))) {
				req.setAttribute("lists", listController.findListByDashboardId(1));
				dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("LIST"));
				
			} else if (uri.equals(PathDiscover.getUri("LIST_CREATE"))) {
				if(req.getParameter("listName") != null) {
					try {
						addList(req.getParameter("listName"), Integer.parseInt(req.getParameter("dashboardId")));
					} catch (Exception e) {
						e.printStackTrace();
					}
					resp.sendRedirect(PathDiscover.getUri("LIST")); // Redirect to lists page.
				} else {
					dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("LIST_CREATE"));
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

	private void addList(String listName, int dashboardId) throws WSObjectException, Exception {
		List l = listController.createList(listName, dashboardId);
		listController.addList(l);
	}
	
}
