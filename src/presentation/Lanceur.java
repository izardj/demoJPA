package presentation;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import metier.Adresse;
import metier.Contact;
import metier.Film;
import metier.LongMetrage;
import metier.TeleFilm;

public class Lanceur {

	public static void main(String[] args) {
		// 1: Ouverture de l'unit� de persistance
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("demojpa-pu");
		EntityManager em = emf.createEntityManager();

		// 2: Ouverture de la transaction
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// 3: Cr�ation d'un Objet m�tier
		Adresse s = new Adresse();
		s.setVille("LYON");
		s.setNumRue("120 rue massena");
		s.setCodePostal("69006");
		
		Contact c = new Contact();
		c.setNom("Izard");
		c.setPrenom("J�r�me");
		c.setEmail("izard.jerome@free.fr");
		c.setAdresse(s);
		
		LongMetrage lm = new LongMetrage();
		lm.setNomFilm("film1");
		lm.setCinema("GAUMONT");
		
		TeleFilm tf = new TeleFilm();
		tf.setChaine("TF1");
		tf.setNomFilm("Josephine");

		Collection<Film> films = new ArrayList<Film>();
		films.add(lm);
		films.add(tf);
		c.setFilms(films);
		
		// 4: Persitance de l'objet m�tier
		em.persist(c);

		// 5: Validation de la transaction
		tx.commit();

		// 6: Fermeture de l'unit� persitance
		em.close();
		emf.close();

	}

}
