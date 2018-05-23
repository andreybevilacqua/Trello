package br.com.ab.Trello.test;

import org.junit.Before;
import org.junit.Test;

import br.com.ab.Trello.servlet.PathDiscover;
import org.junit.Assert;

public class URIDiscoverTest {

	@Before
	public void setup() {
	}
	
	/*@SuppressWarnings("deprecation")
	@Test
	public void discoverURISucessfullyTest() {
		String testURI = PathDiscover.discoverURI(PathDiscover.getUri("DASHBOARD"));
		String testURI2 = PathDiscover.discoverURI(PathDiscover.getUri("DASHBOARD_CREATE"));
		
		Assert.assertFalse(testURI.substring(testURI.length() -1).equals("/"));
		Assert.assertFalse(testURI2.substring(testURI2.length()-1).equals("/"));
		
	}*/

}
