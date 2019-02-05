import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import org.hibernate.hql.internal.ast.tree.IsNotNullLogicOperatorNode;

import main.implementations.ProjectSQLRemote;
import main.implementations.UserSQLLocal;
import main.implementations.UserSQLRemote;
import main.interfaces.IProject;
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
	private static Object object;
	private static IProject iproject;

	public static void main(String[] args) {
		try {
			// Connection to SQL Remote
			emf = Persistence.createEntityManagerFactory("ScrumHibernate");
			em = emf.createEntityManager();
			iuser = new UserSQLRemote();
			iproject = new ProjectSQLRemote();
			fLog = new File("src/main/resources/log.obj");
			// # Falta saber a que instancia cada objeto que se lee
			// # Falta condición para el while
			// # Falta crear los métodos privados en la implementación de UserSQLRemote.

			try {
				if (fLog.exists()) {
					if (fLog.length() > 0) {
						fis = new FileInputStream(fLog);
						ois = new ObjectInputStream(fis);
						object = ois.readObject();
						while (object != null) {
							if (object instanceof User) {
								iuser.insertUser((User) object);
							}else if (object instanceof Project) {
								iproject.insertProject((Project) object);
							}
							object = ois.readObject();
						}
					}
					ois.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			fLog.delete();
			System.out.println("[INFO] - Conexión Online");
		} catch (Exception e) {
			iuser = new UserSQLLocal();
			System.out.println("[INFO] - Conexión Offline");
		}

		// Generate object login:
		Login login = new Login(iuser);
	}
}
