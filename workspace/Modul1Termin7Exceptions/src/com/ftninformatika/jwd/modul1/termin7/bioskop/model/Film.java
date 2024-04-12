package com.ftninformatika.jwd.modul1.termin7.bioskop.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Film {

	// nepromenljiva polja
	private final long id;

	// promenljiva polja
	private String naziv;
	private int trajanje;

	// reference; vidljive u ovom paketu (model) ali ne i spolja
	final Set<Zanr> zanrovi = new LinkedHashSet<>(); // sprečava duplikate
	final Set<Projekcija> projekcije = new LinkedHashSet<>(); // sprečava duplikate

	// statičke funkcije
	public static int compareNaziv(Film film1, Film film2) {
		return film1.naziv.compareTo(film2.naziv);
	}
	
	public static int compareTrajanje(Film film1, Film film2) {
		return Integer.compare(film1.trajanje, film2.trajanje);
	}

	// konstruktori
	public Film(long id, String naziv, int trajanje) {
		this.id = id;
		this.naziv = naziv;
		this.trajanje = trajanje;
	}

	public Film(String naziv, int trajanje) { this(0, naziv, trajanje); }

	public Film() { this(0, "", 0); }

	// metode za povezivanje
	public Collection<Zanr> getZanrovi() {
		return Collections.unmodifiableCollection(zanrovi);
	}

	public void addZanr(Zanr zanr) {
		zanrovi.add(zanr);
		zanr.filmovi.add(this); // unakrsno povezivanje
	}

	public void addAllZanrovi(Collection<Zanr> zanrovi) {
		this.zanrovi.addAll(zanrovi);
		for (Zanr itZanr: zanrovi)
			itZanr.filmovi.add(this); // unakrsno povezivanje
	}
	
	public void removeZanr(Zanr zanr) {
		zanr.filmovi.remove(this); // unakrsno razvezivanje
		zanrovi.remove(zanr);
	}

	public void removeAllZanrovi() {
		for (Zanr itZanr: zanrovi)
			itZanr.filmovi.remove(this); // unakrsno razvezivanje
		zanrovi.clear();
	}
	
	public Collection<Projekcija> getProjekcije() {
		return Collections.unmodifiableCollection(projekcije);
	}

	public void addProjekcija(Projekcija projekcija) {
		projekcije.add(projekcija);
		
		if (projekcija.film != null)
			projekcija.film.removeProjekcija(projekcija); // unakrsno razvezivanje
		projekcija.film = this; // unakrsno povezivanje	
	}
	
	public void addAllProjekcije(Collection<Projekcija> projekcije) {
		this.projekcije.addAll(projekcije);

		for (Projekcija itProjekcija: projekcije) {
			if (itProjekcija.film != null)
				itProjekcija.film.removeProjekcija(itProjekcija); // unakrsno razvezivanje
			itProjekcija.film = this; // unakrsno povezivanje
		}	
	}
	
	public void removeProjekcija(Projekcija projekcija) {
		projekcija.film = null; // unakrsno razvezivanje
		projekcije.remove(projekcija);
	}

	public void removeAllProjekcije() {
		for (Projekcija itProjekcija: projekcije)
			itProjekcija.film = null; // unakrsno razvezivanje
		projekcije.clear();
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
		Film other = (Film) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		// ne ispisivati povezane entitete (zbog moguće rekurzije)
		return "Film [id=" + id + ", naziv=" + naziv + ", trajanje=" + trajanje + "]";
	}
	
	// getter-i i setter-i
	public long getId() {
		return id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

}
