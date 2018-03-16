package br.com.ab.Trello.servlet;

import java.util.HashMap;
import java.util.Map;

public final class PathDiscover {

	static final Map<String, String[]> maps = new HashMap<>();
	//static final String idPattern;
	
	static {
		
		maps.put("CONTEXT", new String[] { "/Trello", "index.jsp" });
		
		// DASHBOARD
		maps.put("DASHBOARD", new String[] { "/Trello/dashboard", "/WEB-INF/views/dashboard/dashboard.jsp" });
		maps.put("DASHBOARD_CREATE", new String[] { "/Trello/dashboard/create", "/WEB-INF/views/dashboard/createDashboard.jsp" });
		maps.put("DASHBOARD_DELETE", new String[] { "/Trello/dashboard/delete/\\d+", "/WEB-INF/views/dashboard/dashboard.jsp"});
		
		// LIST
		maps.put("LIST", new String[] {"/Trello/list", "/WEB-INF/views/list/list.jsp"});
		maps.put("LIST_CREATE", new String[] {"/Trello/list/create", "/WEB-INF/views/list/createList.jsp"});
	}
	
	public static String getUri(String key) {
		return maps.get(key)[0];
	}
	
	public static String getJsp(String key) {
		return maps.get(key)[1];
	}
	
	public static int findObjectId(String uri) {
		int objectId = Integer.parseInt(uri.replaceAll("[^-?0-9]+", ""));
		return objectId;
	}
	
	public static String discoverURI(String uri) {
		return removeContextURI(uri);
	}
	
	private static String removeContextURI(String uri) {
		if(uri.endsWith("/")) {
			uri = uri.substring(0, (uri.length() -1));
		}
		return uri;
	}
}
