import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.implementations.UserSQLLocal;
import main.implementations.UserSQLRemote;
import main.interfaces.IUser;
import main.views.Login;

public class Initialize {
	
	private static IUser iuser;
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static void main(String[] args) {
		try {
			// Connection to SQL Remote
			emf = Persistence.createEntityManagerFactory("ScrumHibernate");
	        em = emf.createEntityManager();
	        iuser = new UserSQLRemote();
	        System.out.println("[INFO] - Conexión Online");
		}catch(Exception e) {
			System.out.println("[INFO] - Conexión Offline");
			iuser = new UserSQLLocal();
		}
		
		// Generate object login:
		Login login = new Login(iuser);
		
		IUser iuser = new UserSQLLocal();
		iuser.getAllUsers();
		System.out.println(iuser.getHashingPassword(iuser.generatePassword()));
	}
}
