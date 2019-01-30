package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import main.implementations.UserSQLRemote;
import main.interfaces.IUser;
import main.models.User;

public class TestConnection {

	public static void main(String[] args) {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumHibernate");
	        EntityManager entityManager = factory.createEntityManager();
	        System.out.println("ONLINE");
	        
//	        entityManager.getTransaction().begin();
//	        
//	        User u1 = new User();
//	        u1.setUserName("AAA");
//	        u1.setUserLastname("AAAAAA");
//	        u1.setUserNickname("AAAAAAA");
//	        u1.setUserPassword("AAA");
//	        u1.setPermissionID(1);
//	        
//	        entityManager.persist(u1);
//	        entityManager.getTransaction().commit();
//	        entityManager.close();
//	        factory.close();
	        
	        IUser iuser = new UserSQLRemote();
//	        iuser.getAllUsers();
//	        iuser.getUserLogin("Amengual", "Contraseña");
//	        User user = iuser.getUserLogged();
//	        System.out.println(user.toString());
//	        System.out.println(iuser.getUserLoggedPermission());
	        
	        iuser.getAllProductOwner();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
