package presentation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import metier.Contact;

public class Lanceur {

	public static void main(String[] args) {
		// 1: Ouverture de l'unit� de persistance
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demojpa-pu");
		EntityManager em = emf.createEntityManager();

		// 2: Ouverture de la transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// 3: Cr�ation d'un Objet m�tier
		Contact c = new Contact();
		c.setNom("Izard");
		c.setPrenom("J�r�me");
		c.setEmail("izard.jerome@free.fr");

		// 4: Persitance de l'objet m�tier
		em.persist(c);

		// 5: Validation de la transaction
		tx.commit();

		// 6: Fermeture de l'unit� persitance
		em.close();
		emf.close();

	}

}