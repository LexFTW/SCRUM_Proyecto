package main.implementations;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



import main.interfaces.IUser;
import main.models.User;
import main.models.UserPermission;

public class UserSQLRemote implements IUser{

	private User userLogged;
	private EntityManagerFactory factory;
	private EntityManager entityManager;
	private Connection connection;
	private Statement statement;
	/*
	 * This constructor load the users from the Remote Database to ArrayList<User>
	 */
	public UserSQLRemote() {
		this.factory = Persistence.createEntityManagerFactory("ScrumHibernate");
        this.entityManager = factory.createEntityManager();
		int primaryKey = 1;
		
		while(entityManager.find(User.class, primaryKey) != null) {
			this.users.add(entityManager.find(User.class, primaryKey));
			primaryKey++;
		}
		
		primaryKey = 1;
		
		while(entityManager.find(UserPermission.class, primaryKey) != null) {
			this.userPermissions.add(entityManager.find(UserPermission.class, primaryKey));
			primaryKey++;
		}
	}


	/*
	 * Search within the ArrayList for the user specified in the login screen.
	 * @param The Username and the password introduced in the login screen.
	 * @return If the username and the password is correct, return the object User with the information about the user logged.
	 * @see main.interfaces.IUser#getUserLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public User getUserLogin(String userName, String password) {
		String passHashed = getHashingPassword(password);
		for (User user : users) {
			if(user.getUserNickname().equals(userName) && user.getUserPassword().equals(passHashed)) {
				this.userLogged = user;
				return this.userLogged;
			}
		}
		return null;
	}

	/*
	 * Return status of connection.
	 * @return A String about the state of the connection.
	 * @see main.interfaces.IUser#getTitleConnection()
	 */
	@Override
	public String getTitleConnection() {
		return " (Online)";
	}

	/*
	 * Return the Object with the information about the user logged.
	 * @return the object about the user logged.
	 * @see main.interfaces.IUser#getUserLogged()
	 */
	@Override
	public User getUserLogged() {
		return this.userLogged;
	}

	/*
	 * Return the name about the permission assigned to the user
	 * @return the String with the information about the permission assigned.
	 */
	@Override
	public String getUserLoggedPermission() {
		try {
			for (UserPermission userPermission : userPermissions) {
				if(userPermission.getPermissionID() == userLogged.getPermissionID()) {
					return userPermission.getPermissionName();
				}
			}
		}catch(Exception e) {
			System.err.println("[ERROR] - No hay ningún usuario logeado, por lo tanto no se puede obtener el tipo de usuario que es.");
		}
		
		return null;
	}


	/*
	 * 
	 * @see main.interfaces.IUser#insertUser(main.models.User)
	 */
	@Override
	public void insertUser(User user) {
		this.users.add(user);
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(user);
		this.entityManager.getTransaction().commit();

//		System.out.println(user.getUserPassword().getClass().getTypeName());
		
		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrum_local_arr.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			this.statement = connection.createStatement();
			
			String sqlQuery = "CREATE TABLE IF NOT EXISTS `users` (\r\n" + 
					"	`UserID`	int ( 11 ) NOT NULL,\r\n" + 
					"	`UserName`	varchar ( 100 ) NOT NULL,\r\n" + 
					"	`UserLastname`	varchar ( 175 ) NOT NULL,\r\n" + 
					"	`UserNickname`	varchar ( 50 ) NOT NULL,\r\n" + 
					"	`UserPassword`	varchar ( 50 ) NOT NULL,\r\n" + 
					"	`UserEmail`	varchar ( 175 ) DEFAULT NULL,\r\n" + 
					"	`PermissionID`	int ( 11 ) NOT NULL,\r\n" + 
					"	`CreatedAt`	timestamp DEFAULT CURRENT_TIMESTAMP,\r\n" + 
					"	`UpdatedAt`	timestamp DEFAULT CURRENT_TIMESTAMP\r\n" + 
					");";

			String sqlQuery3 = "INSERT INTO `users` (UserID, UserName, UserLastname, UserNickname, UserPassword, UserEmail, PermissionID)"
					+ "VALUES(2, '" + user.getUserName() + "', '" + user.getUserLastname() + "', '" + user.getUserNickname() + "', '" +
					user.getUserPassword() + "', '" + user.getUserEmail() + "', " + user.getPermissionID() + ");";
			
			String sqlQuery2 = "INSERT INTO `users` (UserID,UserName,UserLastname,UserNickname,UserPassword,UserEmail,PermissionID,CreatedAt,UpdatedAt) VALUES (1,'AA','AA','AAa','9fab2ee5ba39170d8be10ef547bade2a376f9218',NULL,1,'2019-01-27 14:27:21','2019-01-27 14:27:21')";
			
			statement.executeUpdate(sqlQuery);
			statement.executeUpdate(sqlQuery3);
			System.out.println("Insertao maquina");
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
