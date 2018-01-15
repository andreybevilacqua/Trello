package br.com.ab.Trello.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;
	private String login;
	private String pass;
	
	
	public User(){
		
	}
	
	public User(String login, String password){
		
		this.login = login;
		this.pass = password;
	}
	
	public Integer getId() {
		return userId;
	}

	public void setLogin(String login){
		this.login = login;
	}
	public void setId(Integer user_id) {
		this.userId = user_id;
	}
	
	public String getLogin(){
		return login;
	}
	
	public String getPassword(){
		return pass;
	}
	
	public void setPassword(String password){
		this.pass = password;
	}
}
