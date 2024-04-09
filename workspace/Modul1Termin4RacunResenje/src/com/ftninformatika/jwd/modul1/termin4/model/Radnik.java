package com.ftninformatika.jwd.modul1.termin4.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Radnik extends Korisnik {

	private String adresa;
	private LocalDate datumRegistracije;

	public Radnik(long id, String ime, String prezime, String korisnickoIme, String lozinka, String adresa,
			LocalDate datumRegistracije) {
		super(id, ime, prezime, korisnickoIme, lozinka);
		this.adresa = adresa;
		this.datumRegistracije = datumRegistracije;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public LocalDate getDatumRegistracije() {
		return datumRegistracije;
	}

	public void setDatumRegistracije(LocalDate datumRegistracije) {
		this.datumRegistracije = datumRegistracije;
	}

	@Override
	public String toString() {
		return "Radnik [adresa=" + adresa + ", datumRegistracije=" + datumRegistracije + ", id=" + id + ", ime=" + ime
				+ ", prezime=" + prezime + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + "]";
	}

}
