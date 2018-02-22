package br.com.ab.Trello.servlet;

public enum JSPPath {

	DASHBOARD("/WEB-INF/views/dashboard.jsp"),
	DASHBOARD_CREATE("/WEB-INF/views/createDashboard.jsp");
	
	private final String path;
	
	JSPPath(final String path) {
		this.path = path;
	}
	
	public String toString() {
		return path;
	}
}
