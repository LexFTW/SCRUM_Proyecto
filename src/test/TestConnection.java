package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestConnection {

	public static void main(String[] args) {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("ScrumHibernate");
	        EntityManager entityManager = factory.createEntityManager();
	        System.out.println("ONLINE");
		}catch(Exception e) {
//			System.err.println("No funciona la conexión. Mas información del error: \n" + );
			e.printStackTrace();
		}
	}

}
