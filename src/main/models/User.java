package main.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "users")
public class User {

	private int UserID;
	private String Name;
	private String LastName;
	private String UserName;
	private String Password;
	private int GroupID;
	private Date CreatedAt;
	private Date UpdatedAt;
	
	public User() {
		super();
	}
	
	@Id
	@Column(name ="UserID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	@Column(name ="Name")
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	@Column(name ="LastName")
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	@Column(name ="UserName")
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	@Column(name ="Password")
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Column(name ="GroupID")
	public int getGroupID() {
		return GroupID;
	}
	public void setGroupID(int groupID) {
		GroupID = groupID;
	}
	
	@Column(name ="CreatedAt")
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	
	@Column(name ="UpdatedAt")
	public Date getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "User [UserID=" + UserID + ", Name=" + Name + ", LastName=" + LastName + ", UserName=" + UserName
				+ ", Password=" + Password + ", GroupID=" + GroupID + ", CreatedAt=" + CreatedAt + ", UpdatedAt="
				+ UpdatedAt + "]";
	}
}
