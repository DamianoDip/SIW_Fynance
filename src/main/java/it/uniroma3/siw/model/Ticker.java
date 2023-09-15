package it.uniroma3.siw.model;


import java.util.List;
import java.util.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;




@Entity
public class Ticker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
	private String tickerName;
	

    @OneToOne
	private Azienda azienda;
	
    @Min(0)
	private float max;

    @Min(0)
	private float min;
	
    @Min(0)
	private float attuale;
    @ManyToMany ( mappedBy = "tickers" , cascade = CascadeType.ALL)
    private List<Portafoglio> portafogli;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getTickerName() {
		return tickerName;
	}

	public void setTickerName(String tickerName) {
		this.tickerName = tickerName;
	}

	public Azienda getAzienda() {
		return azienda;
	}

	public void setAzienda(Azienda azienda) {
		this.azienda = azienda;
	}

	public float getMax() {
		return max;
	}

	public void setMax(float max) {
		this.max = max;
	}

	public float getMin() {
		return min;
	}

	public void setMin(float min) {
		this.min = min;
	}

	public float getAttuale() {
		return attuale;
	}

	public void setAttuale(float attuale) {
		this.attuale = attuale;
	}

	@Override
	public int hashCode() {
		return Objects.hash(azienda, id, tickerName);
	}
	

	public List<Portafoglio> getPortafogli() {
		return portafogli;
	}

	public void setPortafogli(List<Portafoglio> portafogli) {
		this.portafogli = portafogli;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticker other = (Ticker) obj;
		return Objects.equals(azienda, other.azienda) && Objects.equals(id, other.id)
				&& Objects.equals(tickerName, other.tickerName);
	}
	
	
	
	

}
