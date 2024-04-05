package com.ftninformatika.jwd.modul1.termin1.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Proizvod {
	private long id;
	private String naziv;
	private double cena;
	final Set<Kategorija> kategorije = new HashSet<>();
	
	
	//metode za povezivanje
	public Collection<Kategorija> getKategorije() {
		return Collections.unmodifiableCollection(this.kategorije);
	}
	
	public void dodajKategoriju(Kategorija kategorija) {
		this.kategorije.add(kategorija);
	}
	
	public void dodajSveKategorije(Collection<Kategorija> kategorije) {
		this.kategorije.addAll(kategorije);
	}
	
	public void ukloniKategoriju(Kategorija kategorija) {
		this.kategorije.remove(kategorija);
	}
	
	public void ukloniSveKategorije()  {
		this.kategorije.clear();
	}
	
	
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
	
	
	
	
	@Override
	public String toString() {
		return "Proizvod [id=" + id + ", naziv=" + naziv + ", cena=" + cena + ", kategorije=" + kategorije + "]";
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
	
	
	

}
