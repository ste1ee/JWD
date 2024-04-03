package com.ftninformatika.jwd.modul1.termin1.model;

public class Rezervacija {
	private long id;
	private String datum;
	private String vremeUlaska;
	private String imeIPrezimeGosta;
	
	//konstruktori
	public Rezervacija(long id, String datum, String vremeUlaska, String imeIPrezimeGosta) {
		super();
		this.id = id;
		this.datum = datum;
		this.vremeUlaska = vremeUlaska;
		this.imeIPrezimeGosta = imeIPrezimeGosta;
	}
	public Rezervacija(String datum, String vremeUlaska, String imeIPrezimeGosta) {
		this(0, datum, vremeUlaska, imeIPrezimeGosta);
	}
	public Rezervacija() {
		this(0, null, "", "");
	}
	
	//getteri i setteri
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getVremeUlaska() {
		return vremeUlaska;
	}
	public void setVremeUlaska(String vremeUlaska) {
		this.vremeUlaska = vremeUlaska;
	}
	public String getImeIPrezimeGosta() {
		return imeIPrezimeGosta;
	}
	public void setImeIPrezimeGosta(String imeIPrezimeGosta) {
		this.imeIPrezimeGosta = imeIPrezimeGosta;
	}
	@Override
	public String toString() {
		return "Rezervacija [id=" + id + ", " + (datum != null ? "datum=" + datum + ", " : "")
				+ (vremeUlaska != null ? "vremeUlaska=" + vremeUlaska + ", " : "")
				+ (imeIPrezimeGosta != null ? "imeIPrezimeGosta=" + imeIPrezimeGosta : "") + "]";
	}
	
	

}
