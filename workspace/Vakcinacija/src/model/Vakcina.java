package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vakcina {

	private long id;
	private String naziv;
	private String tip;
	private int tempCuvanja;

	Set<Prijava> prijave = new HashSet<>();

	public Vakcina(long id, String naziv, String tip, int tempCuvanja) {
		this.id = id;
		this.naziv = naziv;
		this.tip = tip;
		this.tempCuvanja = tempCuvanja;
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
		Vakcina other = (Vakcina) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Vakcina [id=" + id + ", naziv=" + naziv + ", tip=" + tip + ", tempCuvanja=" + tempCuvanja + "]";
	}

	public void addPrijava(Prijava prijava) {
		this.prijave.add(prijava);
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public int getTempCuvanja() {
		return tempCuvanja;
	}

	public void setTempCuvanja(int tempCuvanja) {
		this.tempCuvanja = tempCuvanja;
	}

	public long getId() {
		return id;
	}

}
