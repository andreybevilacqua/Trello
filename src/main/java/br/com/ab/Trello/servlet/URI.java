package br.com.ab.Trello.servlet;

public enum URI {

	ROOT_URI("/Trello"),
	DASHBOARD_URI("/Trello/dashboard"),
	DASHBOARD_CREATE_URI("/Trello/dashboard/create");
	
	private final String uri;
	
	URI(final String uri){
		this.uri = uri;
	}
	
	@Override
	public String toString() {
		return uri;
	}
}
