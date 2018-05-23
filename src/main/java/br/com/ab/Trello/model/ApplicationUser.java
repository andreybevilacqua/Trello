package br.com.ab.Trello.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ApplicationUser")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApplicationUser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlTransient // Ignore this field at the ws request.
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "login", unique = true, nullable = false)
	@XmlElement(required=true)
	private String login;

	@Column(name = "pass", unique = false, nullable = false)
	@XmlElement(required=true)
	private String pass;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "applicationUser")
	private List<Dashboard> dashboards = new ArrayList<Dashboard>();

	// Constructors
	public ApplicationUser(){ }
	
	public ApplicationUser(String login, String password){
		this.login = login;
		this.pass = password;
	}

	// Gets
	public Integer getId() { return userId; }
	
	public String getLogin(){ return login; }
	
	public String getPassword(){ return pass; }

    public ArrayList<Dashboard> getDashboards(){ return (ArrayList<Dashboard>) dashboards; }


    // Sets
    public void setLogin(String login){ this.login = login; }

    public void setId(Integer user_id) { this.userId = user_id; }

	public void setPassword(String password){ this.pass = password; }

	public void setDashboards(ArrayList<Dashboard> dashboards){ this.dashboards = dashboards; }
}
