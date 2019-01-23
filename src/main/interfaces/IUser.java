package main.interfaces;

import java.util.ArrayList;

import main.models.User;
import main.models.UserPermission;

public interface IUser {

	// Atributtes for IUser;
	User user = new User();
	ArrayList<User> users = new ArrayList<>();
	ArrayList<UserPermission> userPermissions = new ArrayList<>();
	
	// Methods;
	public void loadUsers();
	public String getHashingPassword(String password);
	public void getAllUsers();
	public User getUserLogin(String userName,String password);
	public String getTitleConnection();
	public User getUserLogged();
	public String getUserLoggedPermission();
}
