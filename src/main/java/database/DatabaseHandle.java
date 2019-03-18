package database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseHandle {

	// Create an EntityManagerFactory when you start the application.
	private static EntityManager entityManager;
	
	private DatabaseHandle() {
		
	}
	
	public static synchronized EntityManager getEntityManagerFactory()
	{
		if (entityManager == null || ! entityManager.isOpen())
		{
			entityManager = Persistence.createEntityManagerFactory("jpa_game").createEntityManager();
		}
		
		return entityManager;
	}
	
	

}
