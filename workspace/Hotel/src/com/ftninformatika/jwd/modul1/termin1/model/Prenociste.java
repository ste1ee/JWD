package com.ftninformatika.jwd.modul1.termin1.model;

public class Prenociste {
	private long id;
	private double cena;
	private int brojZvezdica;
	
	//konstruktori
	public Prenociste(long id, double cena, int brojZvezdica) {
		super();
		this.id = id;
		this.cena = cena;
		this.brojZvezdica = brojZvezdica;
	}
	public Prenociste(double cena, int brojZvezdica) {
		this(0, cena, brojZvezdica);
	}
	public Prenociste() {
		this(0, 0.0, 0);
	}
	
	
	//getteri i setteri
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public int getBrojZvezdica() {
		return brojZvezdica;
	}
	public void setBrojZvezdica(int brojZvezdica) {
		this.brojZvezdica = brojZvezdica;
	}
	@Override
	public String toString() {
		return "Prenociste [id=" + id + ", cena=" + cena + ", brojZvezdica=" + brojZvezdica + "]";
	}
	
	
	

}
