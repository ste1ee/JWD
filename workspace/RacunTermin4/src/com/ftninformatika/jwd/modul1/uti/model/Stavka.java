package com.ftninformatika.jwd.modul1.uti.model;

import java.util.Objects;

public class Stavka {
	private long id;
	private int kolicina;
	
	Proizvod proizvod;

	public Stavka(long id, int kolicina) {
		super();
		this.id = id;
		this.kolicina = kolicina;
	}
	
	public Stavka() {
		super();
		this.id = 0;
		this.kolicina = 0;
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
		Stavka other = (Stavka) obj;
		return id == other.id;
	}
	
	

	@Override
	public String toString() {
		return "Stavka [id=" + id + ", kolicina=" + kolicina + "]";
	}

	public long getId() {
		return id;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	public Proizvod getProizvod() {
		return proizvod;
	}

	public void setProizvod(Proizvod proizvod) {
		this.proizvod = proizvod;
	}
	
	public void povecajKolicinuZa(int koliko) {
		this.kolicina += koliko;
	}
	
	public void smanjiKolicinuZa(int koliko) {
		this.kolicina -= koliko;
	}
	
	

}
