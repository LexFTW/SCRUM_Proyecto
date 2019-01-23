package main.implementations;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.interfaces.IUser;
import main.models.User;
import main.models.UserPermission;

public class UserSQLRemote implements IUser{

	private User userLogged;
	
	/*
	 * This method load the users from the Remote Database to ArrayList<User>
	 * @see main.interfaces.IUser#loadUsers()
	 */
	@Override
	public void loadUsers() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumHibernate");
        EntityManager entityManager = factory.createEntityManager();
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
	 * This method is responsible for doing 'hashing' on the password entered in the login.
	 * @param The password introduce into the JPasswordField from Login.
	 * @return "The password hashed".
	 * @see main.interfaces.IUser#getHashingPassword(java.lang.String)
	 */
	@Override
	public String getHashingPassword(String password) {
		MessageDigest md = null;
		byte[] result = null;
		
		try {
			try {
				md = MessageDigest.getInstance("SHA-1");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			result = md.digest(password.getBytes("UTF8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
	}

	/*
	 * Print all users of the ArrayList<User>
	 * @see main.interfaces.IUser#getAllUsers()
	 */
	@Override
	public void getAllUsers() {
		for (User user : users) {
			System.out.println(user.toString());
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
				System.out.println("[INFO] - Usuario encontrado!");
				System.out.println("====================================");
				System.out.println("Usuario: " + this.userLogged.getUserNickname());
				System.out.println("Contraseña: " + this.userLogged.getUserPassword());
				System.out.println("====================================");
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

}
