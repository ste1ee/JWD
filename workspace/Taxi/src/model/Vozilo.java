package model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Vozilo {

	private long id;
	private int broj;
	private String vozac;

	private Set<Poziv> pozivi = new HashSet<>();

	public Vozilo(long id, int broj, String vozac) {
		this.id = id;
		this.broj = broj;
		this.vozac = vozac;
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
		Vozilo other = (Vozilo) obj;
		return id == other.id;
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public String getVozac() {
		return vozac;
	}

	public void setVozac(String vozac) {
		this.vozac = vozac;
	}

	public long getId() {
		return id;
	}

	public Collection<Poziv> getPozivi() {
		return Collections.unmodifiableCollection(pozivi);
	}
	
	public void addPoziv(Poziv poziv) {
		if(poziv != null)
			pozivi.add(poziv);
	}

}
