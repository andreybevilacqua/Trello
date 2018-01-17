package br.com.ab.Trello.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlTransient // Ignore this field at the ws request.
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer userId;
	@XmlElement(required=true)
	private String login;
	@XmlElement(required=true)
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
