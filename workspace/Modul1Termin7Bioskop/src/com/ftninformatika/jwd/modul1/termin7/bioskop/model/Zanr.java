package com.ftninformatika.jwd.modul1.termin7.bioskop.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Zanr {
	// nepromenljiva polja
	private final long id;

	// promenljiva polja
	private String naziv;

	// reference; vidljive u ovom paketu (model) ali ne i spolja
	final Set<Film> filmovi = new LinkedHashSet<>(); // sprečava duplikate

	// statičke funkcije
	public static int compareNaziv(Zanr zanr1, Zanr zanr2) {
		return zanr1.naziv.compareTo(zanr2.naziv);
	}

	// konstruktori
	public Zanr(long id, String naziv) {
		this.id = id;
		this.naziv = naziv;
	}

	public Zanr(String naziv) { this(0, naziv); }

	public Zanr() { this(0, ""); }

	// metode za povezivanje
	public Collection<Film> getFilmovi() {
		return Collections.unmodifiableCollection(filmovi);
	}

	public void addFilm(Film film) {
		filmovi.add(film);
		film.zanrovi.add(this); // unakrsno povezivanje
	}

	public void addAllFilmovi(Collection<Film> filmovi) {
		this.filmovi.addAll(filmovi);
		for (Film itFilm: filmovi)
			itFilm.zanrovi.add(this); // unakrsno povezivanje
	}

	public void removeFilm(Film film) {
		film.zanrovi.remove(this); // unakrsno razvezivanje
		filmovi.remove(film);
	}

	public void removeAllFilmovi() {
		for (Film itFilm: filmovi)
			itFilm.zanrovi.remove(this); // unakrsno razvezivanje
		filmovi.clear();
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
		Zanr other = (Zanr) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		// ne ispisivati povezane entitete (zbog moguće rekurzije)
		return "Zanr [id=" + id + ", naziv=" + naziv + "]";
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

}
