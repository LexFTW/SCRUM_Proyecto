package main.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "projects")
public class Project implements Serializable{

	private int projectID;
	private String projectName;
	private String projectDescription;
	private int scrumMasterID;
	private int productOwnerID;
	private Date createdAt;
	private Date updatedAt;
	private boolean updated;
	private boolean inserted;
	private boolean deleted;
	
	public Project(int projectID, String projectName, String projectDescription, int scrumMasterID, int productOwnerID,
			Date createdAt, Date updatedAt, boolean updated, boolean inserted, boolean deleted) {
		super();
		this.projectID = projectID;
		this.projectName = projectName;
		this.projectDescription = projectDescription;
		this.scrumMasterID = scrumMasterID;
		this.productOwnerID = productOwnerID;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.updated = updated;
		this.inserted = inserted;
		this.deleted = deleted;
	}
	
	public Project() {
		super();
	}

	@Id
	@Column(name = "ProjectID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getProjectID() {
		return projectID;
	}
	
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	
	@Column(name = "ProjectTitle")
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
	
	@Column(name ="ScrumMasterID")
	public int getScrumMasterID() {
		return scrumMasterID;
	}
	
	public void setScrumMasterID(int scrumMasterID) {
		this.scrumMasterID = scrumMasterID;
	}
	
	@Column(name ="ProductOwnerID")
	public int getProductOwnerID() {
		return productOwnerID;
	}
	
	public void setProductOwnerID(int productOwnerID) {
		this.productOwnerID = productOwnerID;
	}
	
	@Column(name ="CreatedAt", insertable=false, updatable=false)
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name ="UpdatedAt", insertable=false, updatable=false)
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	@Transient
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Transient
	public boolean isUpdated() {
		return updated;
	}

	@Transient
	public boolean isInserted() {
		return inserted;
	}

	@Transient
	public boolean isDeleted() {
		return deleted;
	}

	@Transient
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	@Transient
	public void setInserted(boolean inserted) {
		this.inserted = inserted;
	}

	@Transient
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Project [projectID=" + projectID + ", projectName=" + projectName + ", projectDescription="
				+ projectDescription + ", scrumMasterID=" + scrumMasterID + ", productOwnerID=" + productOwnerID
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", updated=" + updated + ", inserted="
				+ inserted + ", deleted=" + deleted + "]";
	}
}
