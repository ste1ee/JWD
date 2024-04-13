package com.ftninformatika.jwd.modul1.termin7.bioskop.model;

import java.util.Objects;

public class Korisnik {

	private final String korisnickoIme;

	private String lozinka;
	private String eMail;
	private String pol;
	private boolean administrator;

	public Korisnik(String korisnickoIme, String lozinka, String eMail, String pol, boolean administrator) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.eMail = eMail;
		this.pol = pol;
		this.administrator = administrator;
	}

	@Override
	public int hashCode() {
		return Objects.hash(korisnickoIme);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Korisnik other = (Korisnik) obj;
		return Objects.equals(korisnickoIme, other.korisnickoIme);
	}

	@Override
	public String toString() {
		return "Korisnik [korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + ", eMail=" + eMail + ", pol=" + pol
				+ ", administrator=" + administrator + "]";
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public boolean getAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

}
