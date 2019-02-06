package main.interfaces;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.models.User;
import main.models.UserPermission;

public interface IUser {

	final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    final String NUMERIC = "0123456789";
    final String SPECIAL_CHARS = "!@#$%^&*_=+-/";
    
    
	// Atributtes for IUser;
	User user = new User();
	ArrayList<User> users = new ArrayList<>();
	ArrayList<UserPermission> userPermissions = new ArrayList<>();
	ArrayList<User> productOwners = new ArrayList<>();
	ArrayList<User> scrumMasters = new ArrayList<>();

	
	// Methods;
	public User getUserLogin(String userName,String password);
	public String getTitleConnection();
	public User getUserLogged();
	public String getUserLoggedPermission();
	public void insertUser(User user,boolean replic);
	public ArrayList<User> getAllProductOwner();
	public ArrayList<User> getAllScrumMaster();
	public ArrayList<User> getAllUsers();
	
	
	public default ArrayList<UserPermission> getAllPermissions(){
		if(userPermissions.size() != 0) {
			return userPermissions;
		}else {
			System.err.println("No se han cargado los datos de los permisos correctamente.");
		}
		return null;
	}
	
	/*
	 * This method is responsible for doing 'hashing' on the password entered in the login.
	 * @param The password introduce into the JPasswordField from Login.
	 * @return "The password hashed".
	 */
	public default String getHashingPassword(String password) {
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
	 * This method is responsible for generate a new password for the new user created.
	 * @return the password generated.
	 */
	public default String generatePassword() {
		String dic = ALPHA_CAPS + ALPHA + NUMERIC + SPECIAL_CHARS;
		String password = "";
		
		for (int i = 0; i < 6; i++) {
			int index = new Random().nextInt(dic.length());
	        password += dic.charAt(index);
		}
		
		return password;
	}
}
