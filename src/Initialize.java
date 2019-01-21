import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.implementations.UserSQLRemote;
import main.interfaces.IUser;
import main.views.Login;

public class Initialize {
	
	private static IUser iuser;

	public static void main(String[] args) {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumSQLRemote");
	        EntityManager entityManager = factory.createEntityManager();
	        System.out.println("[INFO] - Conexión Online");
	        iuser = new UserSQLRemote();
		}catch(Exception e) {
			// Connection to SQL Local:
			System.out.println("[INFO] - Conexión Offline");
		}
		
		Login login = new Login();
	}

}
