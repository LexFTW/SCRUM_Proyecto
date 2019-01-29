package main.implementations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
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
		try {
			Class.forName("org.sqlite.JDBC");
			try {
				this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrum_local_aar.db");
				System.out.println("Conexion embebida conectada.");

				this.statement = this.connection.createStatement();
				String sqlQuery = "SELECT * FROM users_permission;";

				this.resultSet = this.statement.executeQuery(sqlQuery);

				while (this.resultSet.next()) {
					this.userPermissions.add(new UserPermission(this.resultSet.getInt("PermissionID"),
							this.resultSet.getString("PermissionName"), this.resultSet.getDate("CreatedAt"),
							this.resultSet.getDate("UpdatedAt")));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUserLogin(String userNickname, String password) {
		if (this.connection != null) {
			try {
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
		} else {
			System.out.println("[ERROR] - No se pudo establecer ninguna conexi�n.");
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
		if (this.connection != null) {
			try {
				statement = connection.createStatement();
				String query = "select * from users_permission where PermissionID = " + userLogged.getPermissionID();
				resultSet = statement.executeQuery(query);

				while (resultSet.next()) {
					return resultSet.getString("PermissionName");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("[ERROR] - No se pudo establecer ninguna conexi�n.");
		}

		return null;
	}

	@Override
	public void insertUser(User user) {
		File fLog = new File("src/main/resources/log");
		FileWriter fw;
		if (fLog.exists()) {
			if (this.connection != null) {
				try {
					fw = new FileWriter(fLog);
					this.connection = DriverManager
							.getConnection("jdbc:sqlite:src/main/resources/bd_scrum_local_aar.db");
					statement = connection.createStatement();

					String query = "insert into users (UserName, UserLastname, UserNickname, UserPassword, UserEmail, PermissionID, CreatedAt, UpdatedAt) VALUES "
							+ "('" + user.getUserName() + "', '" + user.getUserLastname() + "', '" + user.getUserNickname() + "', '" + user.getUserPassword() + 
							"', '" + user.getUserEmail() + "', '" + user.getPermissionID() + "', '" + user.getCreatedAt() + "', '" + user.getUpdatedAt() + "')";

					fw.write(query);
					fw.close();
					this.statement.executeUpdate(query);
					this.statement.close();
					
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}

			}

		} else {
			System.out.println("El archivo especificado no existe");
		}
	}
}
