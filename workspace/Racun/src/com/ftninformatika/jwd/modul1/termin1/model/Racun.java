package com.ftninformatika.jwd.modul1.termin1.model;

public class Racun {
	private long id;
	private String datum;
	private double cena;
	
	
	//konstruktor sa parametrima
	public Racun(long id, String datum, double cena) {
		this.id = id;
		this.datum = datum;
		this.cena = cena;
	}
	
	//konstruktor bez id-a
	public Racun(String datum, double cena) {
		this(0,datum,cena);
	}
	
	//konstruktor bez parametra
	public Racun() {
		this(0,null,0.0);
	}
	
	//getteri
	public long getId() {
		return id;
	}
	
	public String getDatum() {
		return datum;
	}
	
	public double getCena() {
		return cena;
	}
	
	//setteri
	public void setId(long id) {
		this.id = id;
	}
	
	public void setDatum(String datum) {
		this.datum = datum;
	}
	
	public void setCena(double cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Racun [id=" + id + ", datum=" + datum + ", cena=" + cena + "]";
	}
	
	
}
