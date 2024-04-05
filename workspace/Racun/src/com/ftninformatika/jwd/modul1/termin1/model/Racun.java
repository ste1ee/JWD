package com.ftninformatika.jwd.modul1.termin1.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Racun {
	private long id;
	private LocalDateTime datum;
	private double cena;
	
	final Set<Stavka> stavke = new HashSet<>();
	
	
	//konstruktor sa parametrima
	public Racun(long id, LocalDateTime datum, double cena) {
		this.id = id;
		this.datum = datum;
		this.cena = cena;
	}
	
	//konstruktor bez id-a
	public Racun(LocalDateTime datum, double cena) {
		this(0,datum,cena);
	}
	
	//konstruktor bez parametra
	public Racun() {
		this(0,null,0.0);
	}
	
	
	
	public Collection<Stavka> getStavke() {
		return Collections.unmodifiableCollection(this.stavke);
	}
	
	public void dodajStavku(Stavka stavka) {
		this.stavke.add(stavka);
		this.cena += stavka.getKolicina() * stavka.getProizvod().getCena();
	}
	
	public void dodajSveStavke(Collection<Stavka> stavke) {
		this.stavke.addAll(stavke);
		for(Stavka stavka : stavke) {
			this.cena += stavka.getKolicina() * stavka.getProizvod().getCena();
		}
	}
	
	public void ukloniStavku(Stavka stavka) {
		this.stavke.remove(stavka);
		this.cena -= stavka.getKolicina() * stavka.getProizvod().getCena();
	}
	
	public void ukloniSveStavke() {
		this.stavke.clear();
		this.cena = 0.0;
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
		Racun other = (Racun) obj;
		return id == other.id;
	}
	
	
	
	
	
	

	@Override
	public String toString() {
		return "Racun [id=" + id + ", datum=" + datum + ", cena=" + cena + ", stavke=" + stavke + "]";
	}

	
	
	
	//getteri
	public long getId() {
		return id;
	}
	
	public LocalDateTime getDatum() {
		return datum;
	}
	
	public double getCena() {
		return cena;
	}
	
	//setteri
	
	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}
	
	public void setCena(double cena) {
		this.cena = cena;
	}

	
	
	
}
