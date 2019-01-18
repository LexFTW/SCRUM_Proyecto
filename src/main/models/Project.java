package main.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {

	private int projectID;
	private String projectName;
	private String projectDescription;
	private int scrumMasterID;
	private int productOwnerID;
	private Date createdAt;
	
	@Id
	@Column(name = "ProjectID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getProjectID() {
		return projectID;
	}
	
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	
	@Column(name = "ProjectName")
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Column(name = "ProjectDescription")
	public String getProjectDescription() {
		return projectDescription;
	}
	
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	
	public int getScrumMasterID() {
		return scrumMasterID;
	}
	
	public void setScrumMasterID(int scrumMasterID) {
		this.scrumMasterID = scrumMasterID;
	}
	
	public int getProductOwnerID() {
		return productOwnerID;
	}
	
	public void setProductOwnerID(int productOwnerID) {
		this.productOwnerID = productOwnerID;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
