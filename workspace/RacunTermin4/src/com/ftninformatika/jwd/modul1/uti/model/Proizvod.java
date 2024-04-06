package com.ftninformatika.jwd.modul1.uti.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;

public class Proizvod {
	private long id;
	private String naziv;
	private double cena;

	final Set<Kategorija> kategorije = new HashSet<>();

	public Proizvod(long id, String naziv, double cena) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
	}

	public Proizvod() {
		super();
		this.id = 0;
		this.naziv = "";
		this.cena = 0.0;
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
		Proizvod other = (Proizvod) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Proizvod [id=" + id + ", naziv=" + naziv + ", cena=" + cena + "]";
	}

	public long getId() {
		return id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public Collection<Kategorija> getKategorije() {
		return Collections.unmodifiableCollection(kategorije);
	}

	public void addKategorija(Kategorija kategorija) {
		this.kategorije.add(kategorija);
	}

	public void addAllKategorije(Collection<Kategorija> kategorije) {
		this.kategorije.addAll(kategorije);
	}

	public void removeKategorija(Kategorija kategorija) {
		this.kategorije.remove(kategorija);
	}

	public void removeAllKategorije() {
		this.kategorije.clear();
	}

}
