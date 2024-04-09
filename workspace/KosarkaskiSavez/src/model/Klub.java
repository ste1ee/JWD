package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

public class Klub {
	private int sifra;
	private String naziv;
	private java.util.ArrayList<Igrac> sviigraci = new ArrayList<>();

	public Klub(int sifra, String naziv) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
	}

	public Klub(String naziv) {
		this(0, naziv);
	}

	public Klub() {
		this(0, "");
	}

	@Override
	public int hashCode() {
		return Objects.hash(sifra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Klub other = (Klub) obj;
		return sifra == other.sifra;
	}

	@Override
	public String toString() {
		return "Klub [sifra=" + sifra + ", naziv=" + naziv + "]";
	}

	// metode za povezivanje :)
	public Collection<Igrac> getSviigraci() {
		return Collections.unmodifiableCollection(sviigraci);
	}

	public void addIgrac(Igrac igrac) {
		if (igrac.getKlub() != null) {
			igrac.setKlub(this);
		}
		igrac.setKlub(this);
		this.sviigraci.add(igrac);
	}

	public void addAllIgraci(ArrayList<Igrac> sviigraci) {
		for (Igrac i : sviigraci) {
			if (i.getKlub() != null) {
				i.setKlub(this);
			}
			i.setKlub(this);
			this.sviigraci.add(i);
		}
	}

	public void removeIgrac(Igrac igrac) {
		igrac.setKlub(null);
		this.sviigraci.remove(igrac);
	}

	public void removeAllIgraci() {
		for (Igrac i : this.sviigraci) {
			i.setKlub(null);
		}
		this.sviigraci.clear();

	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getSifra() {
		return sifra;
	}

}
