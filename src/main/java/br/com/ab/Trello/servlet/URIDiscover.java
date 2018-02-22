package br.com.ab.Trello.servlet;

import javax.ejb.Stateless;

public class URIDiscover {
	
	public URIDiscover() {
		
	}
	
	public String discoverURI(String uri) {
		uri = removeURIEndBar(uri);
		return uri;
	}
	
	private String removeURIEndBar(String uri) {
		if(uri.substring(uri.length() -1).equals("/")) {
			String finalURI = uri.substring(0, (uri.length() -1));
			return finalURI;
		} else {
			return uri;
		}
	}
}
