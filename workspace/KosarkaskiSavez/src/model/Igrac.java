package model;

import java.util.Objects;

public class Igrac {
	private int sifra;
	private String ime;
	private String prezime;
	Klub klub;

	public Igrac(int sifra, String ime, String prezime) {
		super();
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
	}

	public Igrac(String ime, String prezime) {
		this(0, ime, prezime);
	}

	public Igrac() {
		this(0, "", "");
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
		Igrac other = (Igrac) obj;
		return sifra == other.sifra;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Klub getKlub() {
		return klub;
	}

	public void setKlub(Klub klub) {
		this.klub = klub;
	}

	public int getSifra() {
		return sifra;
	}

	@Override
	public String toString() {
		return "Igrac [sifra=" + sifra + ", ime=" + ime + ", prezime=" + prezime + "]";
	}

}
