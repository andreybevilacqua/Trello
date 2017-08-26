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
	private EntityManager entityManager;
	
	@Resource
	private UserTransaction userTransaction;

	public TestServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = new User();
		user.setLogin("Andrey");
		user.setPassword("teste");

		try{
			userTransaction.begin();
			entityManager.persist(user);
			userTransaction.commit();
		} catch (Exception e){
			throw new ServletException(e);
		}

		response.getWriter().append("Usuario " + user.getLogin() + " criado no banco.").close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
