package it.uniroma3.siw.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;





@Entity
public class Azienda {
	
	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String name; 
	
	@NotNull
	@Min(0)
	private int volume;
	
	@Min(0)
	private int capitalizzazione;
	
	@Min(0)
	private int azioniEmesse;
	
	@OneToOne	
	private Image image ;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getCapitalizzazione() {
		return capitalizzazione;
	}

	public void setCapitalizzazione(int capitalizzazione) {
		this.capitalizzazione = capitalizzazione;
	}

	public int getAzioniEmesse() {
		return azioniEmesse;
	}

	public void setAzioniEmesse(int azioniEmesse) {
		this.azioniEmesse = azioniEmesse;
	}

	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(id, name, volume);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Azienda other = (Azienda) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && volume == other.volume;
	}


	
}
