package model;

import java.time.LocalDate;
import java.util.Objects;

public class Prijava {

	private long id;
	private String jmbg;
	private String imeIPrezime;
	private LocalDate datum;
	Vakcina vakcina;

	public Prijava(long id, String jmbg, String imeIPrezime, Vakcina vakcina, LocalDate datum) {
		this.id = id;
		this.jmbg = jmbg;
		this.imeIPrezime = imeIPrezime;
		this.vakcina = vakcina;
		this.datum = datum;
	}

	public Prijava(long id, String jmbg, String imeIPrezime, LocalDate datum) {
		this(id, jmbg, imeIPrezime, null, datum);
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
		Prijava other = (Prijava) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Prijava [id=" + id + ", jmbg=" + jmbg + ", imeIPrezime=" + imeIPrezime + ", datum=" + datum
				+ ", vakcina=" + vakcina.getNaziv() + "]";
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getImeIPrezime() {
		return imeIPrezime;
	}

	public void setImeIPrezime(String imeIPrezime) {
		this.imeIPrezime = imeIPrezime;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public Vakcina getVakcina() {
		return vakcina;
	}

	public void setVakcina(Vakcina vakcina) {
		this.vakcina = vakcina;
		vakcina.addPrijava(this);
	}

	public long getId() {
		return id;
	}
}
