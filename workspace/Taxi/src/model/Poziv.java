package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Poziv {

	private long id;
	private LocalDateTime datumIVreme;
	private String ulica;
	private int broj;

	Vozilo vozilo;

	public Poziv(long id, LocalDateTime datumIVreme, String ulica, int broj, Vozilo vozilo) {
		super();
		this.id = id;
		this.datumIVreme = datumIVreme;
		this.ulica = ulica;
		this.broj = broj;
		this.vozilo = vozilo;
		if (vozilo != null)
			vozilo.addPoziv(this);
	}

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
		vozilo.addPoziv(this);
	}

	public long getId() {
		return id;
	}

}
