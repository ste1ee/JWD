package model;

import java.util.Objects;

public class Sudija {
	private int sifra;
	private String ime;
	private String prezime;

	public Sudija(int sifra, String ime, String prezime) {
		super();
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
	}

	public Sudija(String ime, String prezime) {
		this(0, ime, prezime);
	}

	public Sudija() {
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
		Sudija other = (Sudija) obj;
		return sifra == other.sifra;
	}

	@Override
	public String toString() {
		return "Sudija [sifra=" + sifra + ", ime=" + ime + ", prezime=" + prezime + "]";
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

	public int getSifra() {
		return sifra;
	}

}
