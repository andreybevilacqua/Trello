package br.com.ab.Trello.servlet;

import java.util.HashMap;
import java.util.Map;

public final class PathDiscover {

	static final Map<String, String[]> maps = new HashMap<>();
	
	static {
		
		maps.put("CONTEXT", new String[] { "/Trello", "index.jsp" });
		
		// DASHBOARD
		maps.put("DASHBOARD", new String[] { "/Trello/dashboard", "/WEB-INF/views/dashboard/dashboard.jsp" });
		maps.put("DASHBOARD_CREATE", new String[] { "/Trello/dashboard/create", "/WEB-INF/views/dashboard/createDashboard.jsp" });
		maps.put("DASHBOARD_DELETE", new String[] { "/Trello/dashboard/delete/\\d+", "/WEB-INF/views/dashboard/dashboard.jsp"});
		maps.put("DASHBOARD_DETAIL", new String[] { "/Trello/dashboard/detail/\\d+", "/WEB-INF/views/dashboard/dashboardDetail.jsp"});
		
		// LIST
		maps.put("LIST_CREATE", new String[] {"/Trello/list/create/\\d+", "/WEB-INF/views/list/createList.jsp"});
        maps.put("LIST_EDIT", new String[] {"/Trello/list/edit/\\d+", "/WEB-INF/views/list/editList.jsp"});
        maps.put("LIST_DELETE", new String[] {"/Trello/list/delete/\\d+", ""});

        // ERROR
		maps.put("ERROR_PAGE", new String[] {"/Trello/error", "/WEB-INF/views/error/errorPage.jsp"});
	}
	
	public static String getUri(String key) { return maps.get(key)[0]; }
	
	public static String getJsp(String key) { return maps.get(key)[1]; }
	
	public static int findObjectId(String uri) {
		int objectId = Integer.parseInt(uri.replaceAll("[^-?0-9]+", ""));
		return objectId;
	}

	public static String removeDashboardIdRegexFromURI(String uri){
		return uri = uri.replace(uri.substring((uri.length() -3), uri.length()), "");
	}

    /*public static String removeListAreaIdRegexFromURI(String uri){
        return uri = uri.replace(uri.substring((uri.length() -4), uri.length()), "");
    }*/

	/*public static String discoverURI(String uri) {
		return removeContextURI(uri);
	}*/
	
	/*private static String removeContextURI(String uri) {
		if(uri.endsWith("/")) {
			uri = uri.substring(0, (uri.length() -1));
		}
		return uri;
	}*/
}
