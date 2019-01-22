package main.implementations;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.interfaces.IUser;
import main.models.User;

public class UserSQLRemote implements IUser{

	private User userLogged;
	
	@Override
	public void loadUsers() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumHibernate");
        EntityManager entityManager = factory.createEntityManager();
		int primaryKey = 1;
		while(entityManager.find(User.class, primaryKey) != null) {
			this.users.add(entityManager.find(User.class, primaryKey));
			primaryKey++;
		}
	}

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

	@Override
	public void getAllUsers() {
		for (User user : users) {
			System.out.println(user.toString());
		}
	}

	@Override
	public User getUserLogin(String userName, String password) {
		String passHashed = getHashingPassword(password);
		System.out.println("Usuario: " + userName);
		System.out.println("Contraseña: " + passHashed);
		for (User user : users) {
			System.out.println(user.toString());
			if(user.getUserNickname().equals(userName) && user.getUserPassword().equals(passHashed)) {
				System.out.println("[INFO] - Usuario encontrado!");
				this.userLogged = user;
				return this.userLogged;
			}
		}
		return null;
	}

	@Override
	public String getTitleConnection() {
		return " (Online)";
	}

	@Override
	public User getUserLogged() {
		System.out.println(userLogged.toString());
		return this.userLogged;
	}

}
