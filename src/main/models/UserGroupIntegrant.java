package main.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users_group_integrants")
public class UserGroupIntegrant {

	// Attributes:
	private int IntegrantID;
	private int GroupID;
	private int UserID;
	private int ProjectID;
	private Date CreatedAt;
	private Date UpdatedAt;
	
	@Id
	@Column(name = "IntegrantID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getIntegrantID() {
		return IntegrantID;
	}
	
	@Column(name = "GroupID")
	public int getGroupID() {
		return GroupID;
	}
	
	@Column(name = "UserID")
	public int getUserID() {
		return UserID;
	}
	
	@Column(name = "ProjectID")
	public int getProjectID() {
		return ProjectID;
	}
	
	@Column(name ="CreatedAt", insertable=false, updatable=false)
	public Date getCreatedAt() {
		return CreatedAt;
	}
	
	@Column(name = "UpdatedAt", insertable=false, updatable=false)
	public Date getUpdatedAt() {
		return UpdatedAt;
	}
	
	public void setIntegrantID(int integrantID) {
		IntegrantID = integrantID;
	}
	
	public void setGroupID(int groupID) {
		GroupID = groupID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	
	public void setProjectID(int projectID) {
		ProjectID = projectID;
	}
	
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}
}
