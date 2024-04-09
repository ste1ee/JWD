package com.ftninformatika.jwd.modul1.termin4.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Proizvod {
	// nepromenljiva polja
	private long id;
		
	// promenljiva polja
	private String naziv;
	
	private double cena;
	
	// reference; vidljive u ovom paketu (model) ali ne i spolja
	final Set<Kategorija> kategorije = new HashSet<>(); // sprečava duplikate
	
	// konstruktori
	public Proizvod(long id, String naziv, double cena) {
		this.id = id;
		this.naziv = naziv;
		this.cena = cena;
	}
	public Proizvod(String naziv, double cena) {
		this(0, naziv, cena);
	}
	
	public Proizvod() {
		this(0,"", 0.0);
	}
	
	// metoda za povezivanje
	public Collection<Kategorija> getKategorije(){
		return Collections.unmodifiableCollection(this.kategorije);
	}

	public void addKategoriju(Kategorija kategorija) {
		this.kategorije.add(kategorija);
	}
	
	public void addAllKategorije(Collection<Kategorija> kategorije) {
		this.kategorije.addAll(kategorije);
	}
	
	public void removeKategoriju(Kategorija kategorija) {
		this.kategorije.remove(kategorija);

	}
	
	public void removeAllKategorije() {
		this.kategorije.clear();
	}
	 
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
    public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
		    return false;
		Proizvod other = (Proizvod) obj;
		return id == other.id;
	}
	   
	@Override
	public String toString() {
		// ne ispisivati povezane entitete (zbog moguće rekurzije)
		return "Proizvod [id=" + id + ", naziv=" + naziv + ", cena=" + cena +"]";
	}

	// getter-i i setter-i
	public long getId() {
		return id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	public void setId(long id) {
		this.id = id;
	}
}