package com.ftninformatika.jwd.modul1.termin6.prodavnica.model;

import java.util.Objects;

public abstract class Proizvod {
	// nepromenljiva polja
	protected final String sifra;

	// promenljiva polja
	protected String naziv;
	protected double cena;

	// konstruktori
	public Proizvod(String sifra, String naziv, double cena) {
		this.sifra = sifra;
		this.naziv = naziv;
		this.cena = cena;
	}

	// metode
	public abstract double cenaPDV();

	// Object metode
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
		Proizvod other = (Proizvod) obj;
		return Objects.equals(sifra, other.sifra);
	}
	
	@Override
	public String toString() {
		return "Proizvod [sifra=" + sifra + ", naziv=" + naziv + ", cena=" + cena + ", cenaPDV=" + cenaPDV() + "]";
	}

	// getter-i i setter-i
	public String getSifra() {
		return sifra;
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

}
