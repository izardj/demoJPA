package metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TELEFILM")
public class TeleFilm extends Film {
	private String chaine;

	public String getChaine() {
		return chaine;
	}

	public void setChaine(String chaine) {
		this.chaine = chaine;
	}

	@Override
	public String toString() {
		return "TeleFilm [chaine=" + chaine + ", toString()=" + super.toString() + "]";
	}

}
