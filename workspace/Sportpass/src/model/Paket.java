package model;

import java.util.Objects;

public class Paket {

	private long id;
	private String naziv;
	private int brtr;
	private int meseci;
	private double cena;

	public Paket(long id, String naziv, int brtr, int meseci, double cena) {
		this.id = id;
		this.naziv = naziv;
		this.brtr = brtr;
		this.meseci = meseci;
		this.cena = cena;
	}

	public Paket(String naziv, int brtr, int meseci, double cena) {
		this(0, naziv, brtr, meseci, cena);
	}

	@Override
	public String toString() {
		return "Paket [id=" + id + ", naziv=" + naziv + ", brtr=" + brtr + ", meseci=" + meseci + ", cena=" + cena
				+ "]";
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
		Paket other = (Paket) obj;
		return id == other.id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public int getBrtr() {
		return brtr;
	}

	public void setBrtr(int brtr) {
		this.brtr = brtr;
	}

	public int getMeseci() {
		return meseci;
	}

	public void setMeseci(int meseci) {
		this.meseci = meseci;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public long getId() {
		return id;
	}

}
