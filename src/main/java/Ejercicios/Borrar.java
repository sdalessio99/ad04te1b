package Ejercicios;


import Entidades.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Borrar {

	/**
	 * 4. OneToMany bidireccional entre entidades Student y University
	 * Borra una Universidad y sus estudiantes.
	 */
	public static void main(String[] args) {

		// sustituir Session por entityManager
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ud4");
		EntityManager entityManager = factory.createEntityManager();
		
		
		try {			
			// crea un objeto Student
			System.out.println("Borrando una universidad sin borrar sus estudiantes");
			
			int university_id = 2;
			
			University tempUniversity = entityManager.find(University.class, university_id);
			// comienza la transacción
			entityManager.getTransaction().begin();
		
			// borra la universidad pero no el estudiante. "on delete set null" en BD
			entityManager.remove(tempUniversity);
			
			// hace commit de la transaccion
			entityManager.getTransaction().commit();
					
			System.out.println("Hecho!");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepci n
			System.out.println("Realizando Rollback");
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			entityManager.close();
			entityManager.close();
		}
	}
	
}