package main.implementations;

import main.interfaces.IUser;
import main.models.User;

public class UserSQLLocal implements IUser{

	@Override
	public User getUserLogin(String userName, String password) {
		return null;
	}

	@Override
	public String getTitleConnection() {
		return " (Offline)";
	}

	@Override
	public User getUserLogged() {
		return null;
	}

	@Override
	public String getUserLoggedPermission() {
		return null;
	}

	@Override
	public void insertUser(User user) {
		
	}

}
