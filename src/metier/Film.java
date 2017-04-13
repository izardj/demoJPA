package metier;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idFilm;
	private String nomFilm;
	@ManyToMany
	private Collection<Contact> contacts;

	public int getIdFilm() {
		return idFilm;
	}

	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}

	public String getNomFilm() {
		return nomFilm;
	}

	public void setNomFilm(String nomFilm) {
		this.nomFilm = nomFilm;
	}

	@Override
	public String toString() {
		return "Film [idFilm=" + idFilm + ", nomFilm=" + nomFilm + "]";
	}

	public Collection<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Collection<Contact> contacts) {
		this.contacts = contacts;
	}
}