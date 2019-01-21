package main.implementations;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.interfaces.IUser;
import main.models.User;

public class UserSQLRemote implements IUser{

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
	public boolean getUserOnline(String userName, String password) {
		String passHashed = getHashingPassword(password);
		for (User user : users) {
			if(user.getUserName().equals(userName) && user.getPassword().equals(passHashed)) {
				return true;
			}
		}
		return false;
	}

}
