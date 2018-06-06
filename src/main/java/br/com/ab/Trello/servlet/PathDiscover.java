package br.com.ab.Trello.servlet;

import java.util.HashMap;
import java.util.Map;

public final class PathDiscover {

	private static final Map<String, String[]> maps = new HashMap<>();
	
	static {
		
		maps.put("CONTEXT", new String[] { "/Trello", "index.jsp" });
		
		// DASHBOARD
		maps.put("DASHBOARD", new String[] { "/Trello/dashboard", "/WEB-INF/views/dashboard/dashboard.jsp" });
		maps.put("DASHBOARD_CREATE", new String[] { "/Trello/dashboard/create", "/WEB-INF/views/dashboard/createDashboard.jsp" });
		maps.put("DASHBOARD_DELETE", new String[] { "/Trello/dashboard/delete/\\d+", "/WEB-INF/views/dashboard/dashboard.jsp"});
		maps.put("DASHBOARD_DETAIL", new String[] { "/Trello/dashboard/detail/\\d+", "/WEB-INF/views/dashboard/dashboardDetail.jsp"});
		
		// LIST
		maps.put("LIST_DETAIL", new String[] {"/Trello/list/detail", "/WEB-INF/views/list/listDetail.jsp"});
		maps.put("LIST_CREATE", new String[] {"/Trello/list/create/\\d+", "/WEB-INF/views/list/createList.jsp"});
        maps.put("LIST_EDIT", new String[] {"/Trello/list/edit", "/WEB-INF/views/list/editList.jsp"});
        maps.put("LIST_DELETE", new String[] {"/Trello/list/delete", ""});

        // CARD
		maps.put("CARD_CREATE", new String[] {"/Trello/card/create", "/WEB-INF/views/card/createCard.jsp"});
		maps.put("CARD_EDIT", new String[] {"/Trello/card/edit", "/WEB-INF/views/card/editCard.jsp"});
		maps.put("CARD_DELETE", new String[] {"/Trello/card/delete", ""});

        // ERROR
		maps.put("ERROR_PAGE", new String[] {"/Trello/error", "/WEB-INF/views/error/errorPage.jsp"});
	}
	
	static String getUri(String key) { return maps.get(key)[0]; }
	
	static String getJsp(String key) { return maps.get(key)[1]; }
	
	static int findObjectId(String uri) {
		return Integer.parseInt(uri.replaceAll("[^-?0-9]+", ""));
	}

	private static String removeDashboardIdRegexFromURI(String uri){
		return uri.replace(uri.substring((uri.length() -3), uri.length()), "");
	}

	static String editRedirectURI(String uri, int dashboardId){
		uri = removeDashboardIdRegexFromURI(uri);
		uri = uri.concat(String.valueOf(dashboardId));
		return uri;
	}

}
