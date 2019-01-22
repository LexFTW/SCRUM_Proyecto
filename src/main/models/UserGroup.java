package main.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users_groups")
public class UserGroup {

	
	private int groupID;
	private String groupName;
	private Date createdAt;
	private Date updateAt;
	
	@Id
	@Column(name="GroupID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	@Column(name="GroupName")
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	@Column(name="CreatedAt", insertable=false, updatable=false)
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		createdAt = createdAt;
	}
	
	@Column(name="UpdatedAt", insertable=false, updatable=false)
	public Date getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(Date updateAt) {
		updateAt = updateAt;
	}
	
	
	
	
	
}
