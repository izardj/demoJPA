package presentation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import metier.Adresse;
import metier.Contact;
import metier.LongMetrage;
import metier.TeleFilm;

public class Lanceur {

	public static void main(String[] args) {
		// 1: Ouverture de l'unité de persistance
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demojpa-pu");
		EntityManager em = emf.createEntityManager();

		// 2: Ouverture de la transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// 3: Création d'un Objet métier
		Adresse s = new Adresse();
		s.setVille("LYON");
		s.setNumRue("120 rue massena");
		s.setCodePostal("69006");
		
		Contact c = new Contact();
		c.setNom("Izard");
		c.setPrenom("Jérôme");
		c.setEmail("izard.jerome@free.fr");
		c.setAdresse(s);
		
		LongMetrage lm = new LongMetrage();
		lm.setNomFilm("film1");
		lm.setCinema("GAUMONT");
		
		TeleFilm tf = new TeleFilm();
		tf.setChaine("TF1");
		tf.setNomFilm("Josephine");

		// 4: Persitance de l'objet métier
		em.persist(c);
		em.persist(lm);
		em.persist(tf);

		// 5: Validation de la transaction
		tx.commit();

		// 6: Fermeture de l'unité persitance
		em.close();
		emf.close();

	}

}
