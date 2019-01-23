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
	        System.out.println("[INFO] - Conexi�n Online");
		}catch(Exception e) {
			// If fail the Remote Connection, I've got the Local Connection:
			System.out.println("[INFO] - Conexi�n Offline");
		}
		
		// Generate object login:
		Login login = new Login(iuser);
	}

}
