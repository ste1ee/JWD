package com.ftninformatika.jwd.modul1.termin1.model;

public class Proizvod {
	private long id;
	private String naziv;
	private double cena;
	
	
	public Proizvod(long id, String naziv, double cena) {
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
	}
	public Proizvod(String naziv, double cena) {
		this(0,naziv,cena);
	}
	public Proizvod() {
		this(0,"",0.0);
	}
	
	
	public long getId() {
		return id;
	}
	public String getNaziv() {
		return naziv;
	}
	public double getCena() {
		return cena;
	}
	
	
	public void setId(long id) {
		this.id = id;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	@Override
	public String toString() {
		return "Proizvod [id=" + id + ", naziv=" + naziv + ", cena=" + cena + "]";
	}
	
	

}
