package main.implementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import main.interfaces.IUser;
import main.models.User;
import main.models.UserPermission;

public class UserSQLRemote implements IUser {

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

		//
		// while (entityManager.find(User.class, primaryKey) != null) {
		// this.users.add(entityManager.find(User.class, primaryKey));
		// primaryKey++;
		// }
		//
		// primaryKey = 1;
		//

		while (entityManager.find(UserPermission.class, primaryKey) != null) {
			this.userPermissions.add(entityManager.find(UserPermission.class, primaryKey));
			primaryKey++;
		}

	}

	/*
	 * Search within the ArrayList for the user specified in the login screen.
	 * 
	 * @param The Username and the password introduced in the login screen.
	 * 
	 * @return If the username and the password is correct, return the object User
	 * with the information about the user logged.
	 * 
	 * @see main.interfaces.IUser#getUserLogin(java.lang.String, java.lang.String)
	 */
	@Override
	public User getUserLogin(String userNickname, String password) {
		List<User> loggedUser = entityManager.createQuery("Select u from User u where UserNickname = '" + userNickname
				+ "' and UserPassword = '" + getHashingPassword(password) + "'").getResultList();

		return userLogged = loggedUser.get(0);
	}

	/*
	 * Return status of connection.
	 * 
	 * @return A String about the state of the connection.
	 * 
	 * @see main.interfaces.IUser#getTitleConnection()
	 */
	@Override
	public String getTitleConnection() {
		return " (Online)";
	}

	/*
	 * Return the Object with the information about the user logged.
	 * 
	 * @return the object about the user logged.
	 * 
	 * @see main.interfaces.IUser#getUserLogged()
	 */
	@Override
	public User getUserLogged() {
		return this.userLogged;
	}

	/*
	 * Return the name about the permission assigned to the user
	 * 
	 * @return the String with the information about the permission assigned.
	 */
	@Override
	public String getUserLoggedPermission() {
		List<UserPermission> permission = entityManager
				.createQuery(
						"Select p from UserPermission p where PermissionID = '" + userLogged.getPermissionID() + "'")
				.getResultList();
		UserPermission up = permission.get(0);

		return up.getPermissionName();
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
		replicarUsuario(user);
	}

	private void replicarUsuario(User user) {
		try {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			this.connection = DriverManager.getConnection("jdbc:sqlite:src/main/resources/bd_scrum_local_aar.db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			this.statement = connection.createStatement();
			String sqlQuery3 = "INSERT INTO `users` (UserID, UserName, UserLastname, UserNickname, UserPassword, UserEmail, PermissionID)"
					+ "VALUES('" + user.getUserID() + "', '" + user.getUserName() + "', '" + user.getUserLastname()
					+ "', '" + user.getUserNickname() + "', '" + user.getUserPassword() + "', '" + user.getUserEmail()
					+ "', " + user.getPermissionID() + ");";

			statement.executeUpdate(sqlQuery3);
			System.out.println("Insertao maquina");
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<User> getAllProductOwner() {
		UserPermission permissionIDPO = (UserPermission) this.entityManager.createQuery("SELECT permiso FROM UserPermission permiso WHERE PermissionName = 'Product Owner'").getSingleResult();
		System.out.println("El ID encontrado para Product Owner es: " + permissionIDPO.getPermissionID());
		ArrayList<User> usersProductOwner = new ArrayList<>(this.entityManager.createQuery("SELECT user FROM User user WHERE PermissionID =" + permissionIDPO.getPermissionID()).getResultList());
		return usersProductOwner;
	}
	
	public ArrayList<User> getAllScrumMaster() {
		UserPermission permissionIDSM = (UserPermission) this.entityManager.createQuery("SELECT permiso FROM UserPermission permiso WHERE PermissionName = 'Scrum Master'").getSingleResult();
		System.out.println("El ID encontrado para Scrum Master es: " + permissionIDSM.getPermissionID());
		ArrayList<User> usersScrumMaster = new ArrayList<>(this.entityManager.createQuery("SELECT user FROM User user WHERE PermissionID =" + permissionIDSM.getPermissionID()).getResultList());
		return usersScrumMaster;
	}

}
