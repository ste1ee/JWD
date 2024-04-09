package com.ftninformatika.jwd.modul1.termin4.model;

public class Admin extends Korisnik {

	public Admin(long id, String ime, String prezime, String korisnickoIme, String lozinka) {
		super(id, ime, prezime, korisnickoIme, lozinka);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", korisnickoIme=" + korisnickoIme
				+ ", lozinka=" + lozinka + "]";
	}

}
