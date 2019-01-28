package main.implementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.interfaces.IUser;
import main.models.User;
import main.models.UserPermission;

public class UserSQLLocal implements IUser {
	private User userLogged;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public UserSQLLocal() {

	}

	@Override
	public User getUserLogin(String userNickname, String password) {
		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrum_local_aar.db");
			System.out.println("Conexion embebida conectada.");

			statement = connection.createStatement();
			String query = "select * from users where UserNickname = '" + userNickname + "' and UserPassword = '"
					+ getHashingPassword(password) + "';";

			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				userLogged = new User(resultSet.getInt("UserID"), resultSet.getString("UserName"),
						resultSet.getString("UserLastname"), resultSet.getString("UserNickname"),
						resultSet.getString("UserPassword"), resultSet.getString("UserEmail"),
						resultSet.getInt("PermissionID"), resultSet.getDate("CreatedAt"),
						resultSet.getDate("UpdatedAt"));
			}

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
		return userLogged;
	}

	@Override
	public String getUserLoggedPermission() {
		try {
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrum_local_aar.db");
			statement = connection.createStatement();

			String query = "select * from users_permission where PermissionID = " + userLogged.getPermissionID();
			resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				return resultSet.getString("PermissionName");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return null;
	}

	@Override
	public void insertUser(User user) {

	}

}
