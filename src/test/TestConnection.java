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
//	        u1.setName("AAA");
//	        u1.setLastName("AAAAAA");
//	        u1.setUserName("AAAAAAA");
//	        u1.setPassword("AAA");
//	        u1.setGroupID(1);
//	        
//	        entityManager.persist(u1);
//	        entityManager.getTransaction().commit();
//	        entityManager.close();
//	        factory.close();
	        
	        IUser iuser = new UserSQLRemote();
	        iuser.loadUsers();
	        iuser.getAllUsers();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
