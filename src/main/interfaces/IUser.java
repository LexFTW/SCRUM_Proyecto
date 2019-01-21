package main.interfaces;

import java.util.ArrayList;

import main.models.User;

public interface IUser {

	// Atributtes for IUser;
	User user = new User();
	User userLogged = new User();;
	ArrayList<User> users = new ArrayList<>();
	
	// Methods;
	public void loadUsers();
	public String getHashingPassword(String password);
	public void getAllUsers();
	public User getUserLogin(String userName,String password);
	
}
