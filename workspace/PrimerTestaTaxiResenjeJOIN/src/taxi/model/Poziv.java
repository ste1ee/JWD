package taxi.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.ftninformatika.jwd.modul1.util.Konzola;

public class Poziv {
	private long id;
	
	private LocalDateTime datumIVreme;
	private String ulica;
	private int broj;
	
	private Vozilo vozilo; // many-to-one veza

	public Poziv(long id, LocalDateTime datumIVreme, String ulica, int broj, Vozilo vozilo) {
		this.id = id;
		this.datumIVreme = datumIVreme;
		this.ulica = ulica;
		this.broj = broj;
		this.vozilo = vozilo;
	}

	// metode za podršku izveštavanja
	public boolean isDatumIVremeUOpsegu(LocalDateTime pocetak, LocalDateTime kraj) {
		return datumIVreme.compareTo(pocetak) >= 0 && datumIVreme.compareTo(kraj) <= 0;
	}

	// Object metode
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Poziv other = (Poziv) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Poziv [id=" + id 
				+ ", datumIVreme=" + Konzola.formatiraj(datumIVreme) 
				+ ", ulica=" + ulica 
				+ ", broj=" + broj 
				+ ", vozilo=" + vozilo.getBroj() + "]";
	}

	// getter-i i setter-i
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDatumIVreme() {
		return datumIVreme;
	}

	public void setDatumIVreme(LocalDateTime datumIVreme) {
		this.datumIVreme = datumIVreme;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public Vozilo getVozilo() {
		return vozilo;
	}

	public void setVozilo(Vozilo vozilo) {
		this.vozilo = vozilo;
	}

}
