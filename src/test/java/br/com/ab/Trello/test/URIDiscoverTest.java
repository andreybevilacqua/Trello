package br.com.ab.Trello.test;

import org.junit.Before;
import org.junit.Test;

import br.com.ab.Trello.servlet.URIDiscover;
import junit.framework.Assert;

public class URIDiscoverTest {

	private URIDiscover uriDiscover;
	
	@Before
	public void setup() {
		uriDiscover = new URIDiscover();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void discoverURISucessfullyTest() {
		String testURI = uriDiscover.discoverURI("/Trello/dashboard/");
		String testURI2 = uriDiscover.discoverURI("/Trello/dashboard");
		
		Assert.assertFalse(testURI.substring(testURI.length() -1).equals("/"));
		Assert.assertFalse(testURI2.substring(testURI2.length()-1).equals("/"));
		
	}

}
