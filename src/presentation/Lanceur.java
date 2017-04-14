package presentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import metier.Adresse;
import metier.Contact;
import metier.Film;
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

		TeleFilm tf2 = new TeleFilm();
		tf2.setChaine("TF1");
		tf2.setNomFilm("Les experts");

		Collection<Film> films = new ArrayList<Film>();
		films.add(lm);
		films.add(tf);
		c.setFilms(films);

		// 4: Persitance de l'objet métier
		em.persist(tf2);
		em.persist(c);

		// 5: Validation de la transaction
		tx.commit();

		// Récupérer le film d'ID 1
		Film f = em.find(Film.class, 1);
		System.out.println(f);

		// Modifier le titre du film
		f.setNomFilm("film modifié");
		tx.begin();
		em.merge(f);
		tx.commit();
		System.out.println(f);

		// Suppresion film d'ID 1
		tx.begin();
		em.remove(f);
		tx.commit();

		// Récupérer tous les films
		List<Film> listeFilm = em.createQuery("SELECT f from Film f").getResultList();
		for (Film fl : listeFilm) {
			System.out.println(fl);
		}

		// Rechercher les films par nom de Film
		Query q = em.createQuery("SELECT f FROM Film f WHERE f.nomFilm = :leNom");
		q.setParameter("leNom", "film1");
		List<Film> listeFilm2 = q.getResultList();
		for (Film fl : listeFilm2) {
			System.out.println(fl);
		}

		// Rechercher les films dont le nom de Film contient la lettre i
		Query q2 = em.createQuery("SELECT f FROM Film f WHERE f.nomFilm LIKE :leNom");
		q2.setParameter("leNom", "%i%");
		List<Film> listeFilm3 = q2.getResultList();
		for (Film fl : listeFilm3) {
			System.out.println(fl);
		}

		// Exemple de getSingleResult()
		Film f5 = (Film) em.createQuery("SELECT f FROM Film f WHERE f.idFilm = 2").getSingleResult();
		System.out.println(f5);

		// Exemple de jointure entre Contact et Adresse
		Query q3 = em.createQuery("SELECT c FROM Contact c LEFT JOIN c.adresse");
		List<Contact> listeC = q3.getResultList();
		for (Contact ct : listeC) {
			System.out.println(ct);
		}

		// Exemple d'utilisation d'une requête nommée (@NamedQuery)
		Query q4 = em.createNamedQuery("Contact.findAll");
		List<Contact> lst = q4.getResultList();
		for (Contact c4 : lst) {
			System.out.println(c4);
		}
		
		// Récupérer toutes les adresses
		List<Adresse> allAdr = em.createNamedQuery("Adresse.findAll").getResultList();
		for (Adresse adresse : allAdr) {
			System.out.println(adresse);
		}
		
		// Récupérer les adresses dont le nom de rue contient un mot
		Query q5 = em.createNamedQuery("Adresse.findByRue");
		q5.setParameter("numRue", "%a%");
		List<Adresse> keywordAdr = q5.getResultList();
		for (Adresse adresse : keywordAdr) {
			System.out.println(adresse);
		}
		
		// 6: Fermeture de l'unité persitance
		em.close();
		emf.close();

	}

}
