import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.implementations.UserSQLLocal;
import main.implementations.UserSQLRemote;
import main.interfaces.IUser;
import main.models.Project;
import main.models.User;
import main.views.Login;

public class Initialize {

	private static IUser iuser;
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static File fLog;
	private static User user;
	private static FileInputStream fis;
	private static ObjectInputStream ois;

	public static void main(String[] args) {
		try {
			// Connection to SQL Remote
			emf = Persistence.createEntityManagerFactory("ScrumHibernate");
			em = emf.createEntityManager();
			iuser = new UserSQLRemote();
			fLog = new File("src/main/resources/log.obj");
			// # Falta saber a que instancia cada objeto que se lee
			// # Falta condición para el while
			// # Falta crear los métodos privados en la implementación de UserSQLRemote.

			if (fLog.exists()) {
				if (fLog.length() > 0) {
					fis = new FileInputStream(fLog);
					ois = new ObjectInputStream(fis);
					ois = new ObjectInputStream(fis);
					User u = (User) ois.readObject();
					System.out.println(u.toString());
					
//					while (!salir) {
//						if ((ois.readObject() instanceof User)) {
//							System.out.println("Usuario");
//							User usuario = (User) ois.readObject();
//						}else if ((ois.readObject() instanceof Project)) {
//							System.out.println("Proyecto");
//							Project proyecto = (Project) ois.readObject();
//						}
//						
//						if(ois.readObject() == null) {
//							salir = true;
//						}
//						
//						fis.close();
//						ois.close();
//					}
				}
			}
			
			System.out.println("[INFO] - Conexión Online");
		} catch (Exception e) {
			System.out.println(e.toString());
			iuser = new UserSQLLocal();
			System.out.println("[INFO] - Conexión Offline");
		}

		// Generate object login:
		Login login = new Login(iuser);
	}
}
