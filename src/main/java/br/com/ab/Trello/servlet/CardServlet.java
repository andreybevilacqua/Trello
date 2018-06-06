package br.com.ab.Trello.servlet;

import br.com.ab.Trello.controller.CardController;
import br.com.ab.Trello.controller.ListAreaController;
import br.com.ab.Trello.model.Card;
import br.com.ab.Trello.model.ListArea;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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

    private int listAreaId;
    private int dashboardId;
    private int cardId;

    private ListArea listArea;

    private RequestDispatcher dispatcher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        executeURI(req.getRequestURI(), req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void prepareListAreaForCardServlet(String stringListAreaId, HttpServletRequest req){
        this.listAreaId = Integer.parseInt(req.getParameter("listAreaId"));
        this.listArea = findListAreaByListAreaId(this.listAreaId);
        req.setAttribute("listAreaId", this.listAreaId);
    }

    private void executeURI(String uri, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        //RequestDispatcher dispatcher;

        String title = req.getParameter("cardName");

        try {

            dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("CARD_CREATE"));

            if(uri.matches(PathDiscover.getUri("CARD_CREATE"))) {

                if(title != null){
                    try{
                        this.listArea = findListAreaByListAreaId(this.listAreaId);
                        addNewCard(title, this.listArea);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    this.dashboardId = findDashboardIdByListArea(this.listArea);
                    resp.sendRedirect(PathDiscover.editRedirectURI(PathDiscover.getUri("DASHBOARD_DETAIL"), this.dashboardId));
                    // REDIRECIONAR PARA LIST DETAILS
                } else {
                    prepareListAreaForCardServlet(req.getParameter("listAreaId"), req);
                    req.setAttribute("listArea", this.listArea);
                    this.dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("CARD_CREATE"));
                }


            } else if (uri.matches(PathDiscover.getUri("CARD_DELETE"))) {

                this.cardId = Integer.parseInt(req.getParameter("cardId"));

                if(this.cardId > -1){
                    deleteCard(this.cardId);
                    setRequestDispatcherAndRespRedirectByPathDiscoverKey("LIST_DETAIL", req, resp);
                } else{
                    this.dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("ERROR_PAGE"));
                }

            }  else if (uri.matches(PathDiscover.getUri("CARD_EDIT"))) {

                if(title != null){
                    try{
                        Card card = cardController.findCardById(this.cardId);
                        cardController.editCardTitle(title, this.cardId);
                        req.setAttribute("card", card);
                        req.setAttribute("listAreaId",  card.getListArea().getId());
                    } catch (Exception e){
                        e.printStackTrace();
                    }

                    setRequestDispatcherAndRespRedirectByPathDiscoverKey("LIST_DETAIL", req, resp);

                } else {
                    this.cardId = Integer.parseInt(req.getParameter("cardId"));
                    Card card = cardController.findCardById(this.cardId);
                    req.setAttribute("card", card);
                    dispatcher = req.getRequestDispatcher(PathDiscover.getJsp("CARD_EDIT"));
                }
            }

            dispatcher.forward(req, resp);
            //resp.sendRedirect(PathDiscover.getUri("LIST_DETAIL"));

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

    private ListArea findListAreaByListAreaId(int listAreaId){
        return listAreaController.findListAreaById(listAreaId);
    }

    private void deleteCard(int cardId){
        cardController.deleteCard(cardId);
    }

    private int findDashboardIdByListArea(ListArea listArea){
        return listArea.getDashboard().getId();
    }

    private void setRequestDispatcherAndRespRedirectByPathDiscoverKey(String key, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.dispatcher = req.getRequestDispatcher(PathDiscover.getJsp(key));
        //resp.sendRedirect(PathDiscover.getUri(key));
    }

}

