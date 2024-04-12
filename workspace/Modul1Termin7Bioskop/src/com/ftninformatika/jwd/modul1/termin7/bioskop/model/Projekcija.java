package com.ftninformatika.jwd.modul1.termin7.bioskop.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Projekcija {
	// nepromenljiva polja
	private final long id;

	// promenljiva polja
	private LocalDateTime datumIVreme;
	private int sala;
	private String tip;
	private double cenaKarte;

	// reference; vidljive u ovom paketu (model) ali ne i spolja
	Film film;

	// statičke funkcije
	public static int compareDatumIVreme(Projekcija projekcija1, Projekcija projekcija2) {
		return projekcija1.datumIVreme.compareTo(projekcija2.datumIVreme);
	}
	
	public static int compareFilm(Projekcija projekcija1, Projekcija projekcija2) {
		return Film.compareNaziv(projekcija1.film, projekcija2.film);
	}

	public static int compareSala(Projekcija projekcija1, Projekcija projekcija2) {
		return Integer.compare(projekcija1.sala, projekcija2.sala);
	}

	public static int compareTip(Projekcija projekcija1, Projekcija projekcija2) {
		return projekcija1.tip.compareTo(projekcija2.tip);
	}

	public static int compareCenaKarte(Projekcija projekcija1, Projekcija projekcija2) {
		return Double.compare(projekcija1.cenaKarte, projekcija2.cenaKarte);
	}

	// konstruktori
	public Projekcija(long id, LocalDateTime datumIVreme, Film film, int sala, String tip, double cenaKarte) {
		this.id = id;
		this.datumIVreme = datumIVreme;
		this.sala = sala;
		this.tip = tip;
		this.cenaKarte = cenaKarte;

		this.film = film;
		if (film != null)
			film.projekcije.add(this);
	}

	public Projekcija(LocalDateTime datumIVreme, Film film, int sala, String tip, double cenaKarte) {
		this(0, datumIVreme, film,  sala, tip, cenaKarte);
	} 

	public Projekcija () { this(0, LocalDateTime.now(), null, 0, "", Double.MAX_VALUE); } 

	// metode za povezivanje
	public void setFilm(Film film) {
		if (this.film != null)
			this.film.projekcije.remove(this); // razvezivanje

		this.film = film;
		if (this.film != null)
			this.film.projekcije.add(this); // unakrsno povezivanje
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
		Projekcija other = (Projekcija) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		// ispisuje se samo naziv filma
		return "Projekcija [id=" + id + ", datumIVreme=" + datumIVreme + ", film=" + film.getNaziv() + ", sala=" + sala + ", tip=" + tip
				+ ", cenaKarte=" + cenaKarte + "]";
	}

	// getter-i i setter-i
	public long getId() {
		return id;
	}

	public LocalDateTime getDatumIVreme() {
		return datumIVreme;
	}

	public void setDatumIVreme(LocalDateTime datumIVreme) {
		this.datumIVreme = datumIVreme;
	}

	public Film getFilm() {
		return film;
	}
	
	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

}
