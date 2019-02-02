package main.implementations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
				
				getConnectionLocal();
				
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
			}finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void getConnectionLocal() {
		try {
			Class.forName("org.sqlite.JDBC");
			try {
				this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrum_local_aar.db");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUserLogin(String userNickname, String password) {
		getConnectionLocal();
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
			}finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} else {
			System.out.println("[ERROR] - No se pudo establecer ninguna conexión.");
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
		getConnectionLocal();
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
			}finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("[ERROR] - No se pudo establecer ninguna conexión.");
		}

		return null;
	}

	@Override
	public void insertUser(User user) {
		this.getConnectionLocal();
		if(this.connection != null) {
			try {
				this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrum_local_aar.db");
				statement = connection.createStatement();

				String query = "insert into users (UserName, UserLastname, UserNickname, UserPassword, UserEmail, PermissionID, CreatedAt, UpdatedAt) VALUES "
						+ "('" + user.getUserName() + "', '" + user.getUserLastname() + "', '" + user.getUserNickname() + "', '" + user.getUserPassword() + 
						"', '" + user.getUserEmail() + "', '" + user.getPermissionID() + "', '" + user.getCreatedAt() + "', '" + user.getUpdatedAt() + "')";

				this.statement.executeUpdate(query);
				this.statement.close();
				
				serializeUser(user);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public ArrayList<User> getAllProductOwner() {
		ArrayList<User> users = new ArrayList<>();
		if(this.connection != null) {
			try {
				this.statement = this.connection.createStatement();
				this.resultSet = this.statement.executeQuery("SELECT * FROM users WHERE PermissionID = (SELECT PermissionID FROM users_permission WHERE PermissionName = 'Product Owner');");
				
				while(this.resultSet.next()) {
					User user = new User();
					user.setUserID(this.resultSet.getInt("UserID"));
					user.setUserName(this.resultSet.getString("UserName"));
					user.setUserLastname(this.resultSet.getString("UserLastname"));
					user.setUserNickname(this.resultSet.getString("UserNickname"));
					user.setUserPassword(this.resultSet.getString("UserPassword"));
					user.setUserEmail(this.resultSet.getString("UserEmail"));
					user.setPermissionID(this.resultSet.getInt("PermissionID"));
					user.setCreatedAt(this.resultSet.getDate("CreatedAt"));
					user.setUpdatedAt(this.resultSet.getDate("UpdatedAt"));
					users.add(user);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	@Override
	public ArrayList<User> getAllScrumMaster() {
		ArrayList<User> users = new ArrayList<>();
		getConnectionLocal();
		if(this.connection != null) {
			try {
				this.statement = this.connection.createStatement();
				this.resultSet = this.statement.executeQuery("SELECT * FROM users WHERE PermissionID = (SELECT PermissionID FROM users_permission WHERE PermissionName = 'Scrum Master');");
				
				while(this.resultSet.next()) {
					User user = new User();
					user.setUserID(this.resultSet.getInt("UserID"));
					user.setUserName(this.resultSet.getString("UserName"));
					user.setUserLastname(this.resultSet.getString("UserLastname"));
					user.setUserNickname(this.resultSet.getString("UserNickname"));
					user.setUserPassword(this.resultSet.getString("UserPassword"));
					user.setUserEmail(this.resultSet.getString("UserEmail"));
					user.setPermissionID(this.resultSet.getInt("PermissionID"));
					user.setCreatedAt(this.resultSet.getDate("CreatedAt"));
					user.setUpdatedAt(this.resultSet.getDate("UpdatedAt"));
					users.add(user);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	@Override
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = new ArrayList<>();
		this.getConnectionLocal();
		if(this.connection != null) {
			try {
				this.statement = this.connection.createStatement();
				this.resultSet = this.statement.executeQuery("SELECT * FROM users");
				while(this.resultSet.next()) {
					User user = new User();
					user.setUserID(this.resultSet.getInt("UserID"));
					user.setUserName(this.resultSet.getString("UserName"));
					user.setUserLastname(this.resultSet.getString("UserLastname"));
					user.setUserNickname(this.resultSet.getString("UserNickname"));
					user.setUserPassword(this.resultSet.getString("UserPassword"));
					user.setUserEmail(this.resultSet.getString("UserEmail"));
					user.setPermissionID(this.resultSet.getInt("PermissionID"));
					user.setCreatedAt(this.resultSet.getDate("CreatedAt"));
					user.setUpdatedAt(this.resultSet.getDate("UpdatedAt"));
					users.add(user);
				}
				this.resultSet.close();
				this.statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					this.connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return users;
	}
	
	public void serializeUser(User user) {
		try {
			ObjectOutputStream userWriter = new ObjectOutputStream(new FileOutputStream("src/main/resources/log.obj", true));
			user.setInserted(true);
			userWriter.writeObject(user);
			userWriter.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo log para registrar la información, pongase en contacto con el Administrador", "No se ha encontrado el archivo",  JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error de E/S al archivo log, pongase en contacon con el Administrador", "Error de E/S",  JOptionPane.ERROR_MESSAGE);
		}
	}

}
