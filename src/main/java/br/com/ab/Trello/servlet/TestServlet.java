package br.com.ab.Trello.servlet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import br.com.ab.Trello.model.User;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private UserTransaction ut;

	public TestServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User u = new User();
		u.setLogin("Andrey");
		u.setPassword("teste");

		try{
			ut.begin();
			em.persist(u);
			ut.commit();
		} catch (Exception e){
			throw new ServletException(e);
		}

		response.getWriter().append("Usuario " + u.getLogin() + " criado no banco.").close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
