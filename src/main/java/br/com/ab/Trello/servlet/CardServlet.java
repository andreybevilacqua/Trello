package br.com.ab.Trello.servlet;

import br.com.ab.Trello.controller.CardController;
import br.com.ab.Trello.controller.ListAreaController;
import br.com.ab.Trello.model.Card;
import br.com.ab.Trello.model.ListArea;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CardServlet", urlPatterns = {"/card", "/card/*"})
public class CardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private CardController cardController;
    
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

    private void executeURI(String uri, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        RequestDispatcher dispatcher;

        int listAreaId = Integer.parseInt(req.getParameter("listAreaId"));

        String title = req.getParameter("cardName");

        try {

            dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("CARD_CREATE"));

            if(uri.matches(PathDiscover.getUri("CARD_CREATE"))) {

                if(title != null){
                    try{
                        ListArea listArea = findListAreaById(listAreaId);
                        addNewCard(title, listArea);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    resp.sendRedirect(PathDiscover.getUri("DASHBOARD_DETAIL"));
                } else {
                    dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("CARD_CREATE"));
                }


            } else if (uri.matches(PathDiscover.getUri("CARD_DELETE"))) {

            }  else if (uri.matches(PathDiscover.getUri("CARD_EDIT"))) {

                if(title != null){

                } else {
                    dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("CARD_CREATE"));
                }
            }


        } catch (NullPointerException npex) {
            dispatcher = req.getRequestDispatcher("/");

            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void addNewCard(String title, ListArea listArea) {
        Card card = cardController.createNewCard(title, listArea);
        cardController.addCard(card);
    }

    private ListArea findListAreaById(int listAreaId){
        return listAreaController.findListAreaById(listAreaId);
    }

}

