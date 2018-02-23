package br.com.ab.Trello.servlet;

import java.util.HashMap;
import java.util.Map;

public final class PathDiscover {

	static final Map<String, String> uriMap = new HashMap<String, String>();
	static final Map<String, String> jspMap = new HashMap<String, String>();
	
	public PathDiscover() {
		uriMap.put("CONTEXT", "/Trello");
		uriMap.put("DASHBOARD", "/dashboard");
		uriMap.put("DASHBOARD_CREATE", "/dashboard/create");
		
		jspMap.put("CONTEXT", "index.jsp");
		jspMap.put("DASHBOARD", "/WEB-INF/views/dashboard.jsp");
		jspMap.put("DASHBOARD_CREATE", "/WEB-INF/views/createDashboard.jsp");
	}
	
	public static String getUri(String key) {
		return uriMap.get(key);
	}
	
	public static String getJsp(String key) {
		return jspMap.get(key);
	}
	
	public static String discoverURI(String uri) {
		return removeURIEndBar(uri);
	}
	
	private static String removeURIEndBar(String uri) {
		if(uri.endsWith("/")) {
			uri = uri.substring(0, (uri.length() -1));
		}
		return uri;
	}
}
