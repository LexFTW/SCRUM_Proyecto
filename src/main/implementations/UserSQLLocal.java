package main.implementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.interfaces.IUser;
import main.models.User;

public class UserSQLLocal implements IUser {
	private User userLogged;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public UserSQLLocal() {

	}

	@Override
	public User getUserLogin(String userName, String password) {

		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrum_local_aar.db");
			System.out.println("Conexion embebida conectada.");

			statement = connection.createStatement();
			String query = "select * from users where userName = '" + userName + "' and userPassword = '" + getHashingPassword(password)
					+ "';";

			resultSet = statement.executeQuery(query);
			resultSet.next();
			System.out.println(resultSet.getString(1));

//			userLogged = new User(resultSet.getInt("UserID"),resultSet.getString("UserName"),resultSet.getString("UserLastname"),resultSet.getString("UserNickname"),resultSet.getString("UserPassword"),resultSet.getString("UserEmail"),resultSet.getInt("PermissionID"),resultSet.getDate("CreatedAt"), resultSet.getDate("UpdatedAt"));

			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return userLogged;
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
