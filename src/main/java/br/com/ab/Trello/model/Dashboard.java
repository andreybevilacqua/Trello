package br.com.ab.Trello.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Dashboard")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Dashboard {

	@XmlTransient
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer dashboardId;

	@XmlElement(required=true)
	@Column(nullable = false)
	private String title;

	@XmlElement(required=true)
    @ManyToOne()
	@JoinColumn(nullable = false)
	private ApplicationUser applicationUser;

	@OneToMany(mappedBy = "dashboard")
	private List<ListArea> listAreas = new ArrayList<ListArea>();

	// Constructors
	public Dashboard(){ }

	public Dashboard(String title, ApplicationUser applicationUser){
		setTitle(title);
		setApplicationUser(applicationUser);
	}

	public Dashboard(int id, String title, ApplicationUser applicationUser){
		setTitle(title);
		setApplicationUser(applicationUser);
	}

	// Gets
	public String getTitle() { return title; }

	public int getId(){ return dashboardId; }
	
	public ApplicationUser getApplicationUser() { return applicationUser; }

	public List<ListArea> getListAreas(){ return listAreas;}

	public int getNumberOfLists() { return listAreas.size(); }

	// Sets
	public void setApplicationUser(ApplicationUser applicationUser) { this.applicationUser = applicationUser; }

	public void setId(int id){ this.dashboardId = id; }

	public void setTitle(String title) { this.title = title; }

}
