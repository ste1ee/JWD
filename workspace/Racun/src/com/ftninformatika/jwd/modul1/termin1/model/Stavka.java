package com.ftninformatika.jwd.modul1.termin1.model;

public class Stavka {
	private long id;
	private int kolicina;
	Racun racun;
	
	public Stavka(long id, int kolicina) {
		this.id = id;
		this.kolicina = kolicina;
	}
	public Stavka(int kolicina) {
		this(0,kolicina);
	}
	public Stavka() {
		this(0,0);
	}
	
	public long getId() {
		return id;
	}
	public int getKolicina() {
		return kolicina;
	}
	public Racun getRacun() {
		return racun;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public void setRacun(Racun racun) {
		this.racun = racun;
	}
	@Override
	public String toString() {
		return "Stavka [id=" + id + ", kolicina=" + kolicina + ", racun=" + racun + "]";
	}
	
	
	

}
