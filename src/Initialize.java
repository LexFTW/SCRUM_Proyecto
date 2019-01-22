import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
	        iuser.loadUsers();
	        System.out.println("[INFO] - Conexión Online");
		}catch(Exception e) {
			// Connection to SQL Local:
			System.out.println("[INFO] - Conexión Offline");
		}
		
		Login login = new Login(iuser);
	}

}
