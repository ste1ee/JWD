package com.ftninformatika.jwd.modul1.termin3.modelovanje;

import java.util.Objects;

public class Stavka {
	private long id;
	private int kolicina;
	Proizvod proizvod;
	Racun racun;
	
	//konstruktori
	public Stavka(long id, int kolicina, Racun racun) {
		super();
		this.id = id;
		this.kolicina = kolicina;
		this.racun = racun;
	}
	public Stavka(int kolicina, Racun racun) {
		this(0, kolicina, racun);
	}
	public Stavka() {
		this(0, 0, null);
	}
	
	//hashcode
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
		Stavka other = (Stavka) obj;
		return id == other.id;
	}
	
	
	//metoda za povezivanje
	public void dodajProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
		proizvod.stavke.add(this);
	}
	public void ukloniProizvod(Proizvod proizvod) {
		this.proizvod = null;
		proizvod.stavke.remove(this);
	}
	
	public void dodajRacun(Racun racun) {
		this.racun = racun;
		racun.stavke.add(this);
	}
	public void ukloniRacun(Racun racun) {
		racun.stavke.remove(this);
		this.racun = null;
	}
	
	//getteri i setteri
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public long getId() {
		return id;
	}
	
	public Proizvod getProizvod() {
		return proizvod;
	}
	//metode za dodavanje i smanjivanje kolicine
	
	public void dodajKolicinuZa(int koliko) {
		this.kolicina += koliko;
	}
	
	public void umanjiKolicinuZa(int koliko) {
		this.kolicina -= koliko;
	}
	
	//tostring
	
	@Override
	public String toString() {
		return "Stavka [id=" + id + ", kolicina=" + kolicina + "]";
	}
	
	
	

}
