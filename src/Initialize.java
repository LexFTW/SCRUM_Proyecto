import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;

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
import main.models.Specification;
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
			fLog = new File("src/main/resources/log");
			try {
				if (fLog.exists()) {
					if (fLog.length() > 0) {
						fis = new FileInputStream(fLog);
						ois = new ObjectInputStream(fis);
						object = ois.readObject();
						while (object != null) {
							if (object instanceof User) {
								User u = (User) object;
								if (u.isInserted()) {
									iuser.insertUser(u, false);
								} else if (u.isDeleted()) {

								} else if (u.isUpdated()) {

								}
							} else if (object instanceof Project) {
								Project p = (Project) object;
								if (p.isInserted()) {
									iproject.insertProject((Project) object, false);
								} else if (p.isDeleted()) {

								} else if (p.isUpdated()) {

								}
							} else if(object instanceof Specification) {
								iproject.insertSpecification((Specification) object, false);
							}
							object = ois.readObject();
						}
					}
					ois.close();
				}
			} catch (Exception e) {
				try (FileChannel fC = new FileOutputStream(fLog, true).getChannel()) {
					fC.truncate(0);
				}
			}
			System.out.println("[INFO] - Conexión Online");
		} catch (Exception e) {
			iuser = new UserSQLLocal();
			System.out.println("[INFO] - Conexión Offline");
		}

		// Generate object login:
		Login login = new Login(iuser);
	}
}
