package main.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	private String UserName;
	private String UserLastname;
	private String UserNickname;
	private String UserPassword;
	private int PermissionID;
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
	@Column(name ="UserName")
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String name) {
		UserName = name;
	}
	@Column(name ="UserLastname")
	public String getUserLastname() {
		return UserLastname;
	}
	public void setUserLastname(String userLastname) {
		UserLastname = userLastname;
	}
	@Column(name ="UserNickname")
	public String getUserNickname() {
		return UserNickname;
	}
	public void setUserNickname(String userNickname) {
		UserNickname = userNickname;
	}
	@Column(name ="UserPassword")
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	@Column(name ="PermissionID")
	public int getPermissionID() {
		return PermissionID;
	}
	public void setPermissionID(int permissionID) {
		this.PermissionID = permissionID;
	}
	
	@Column(name ="CreatedAt", insertable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(Date createdAt) {
		CreatedAt = createdAt;
	}
	
	@Column(name ="UpdatedAt", insertable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		UpdatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "User [UserID=" + UserID + ", UserName=" + UserName + ", UserLastname=" + UserLastname
				+ ", UserNickname=" + UserNickname + ", UserPassword=" + UserPassword + ", PermissionID=" + PermissionID
				+ ", CreatedAt=" + CreatedAt + ", UpdatedAt=" + UpdatedAt + "]";
	}
}
