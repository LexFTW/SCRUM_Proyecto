package main.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "sprints")
public class Sprint {

	private int sprintID;
	private Date sprintTime;
	private int projectID;
	private Date createAt;
	private Date updatedAt;
	
	@Id
	@Column(name ="SprintID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getSprintID() {
		return sprintID;
	}
	public void setSprintID(int sprintID) {
		this.sprintID = sprintID;
	}
	@Column(name ="SprintTime")
	public Date getSprintTime() {
		return sprintTime;
	}
	public void setSprintTime(Date sprintTime) {
		this.sprintTime = sprintTime;
	}
	@Column(name = "ProjectID")
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	
	@Column(name ="CreatedAt", insertable=false, updatable=false)
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	@Column(name = "UpdatedAt", insertable=false, updatable=false)
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
