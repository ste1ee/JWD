package com.ftninformatika.jwd.modul1.termin3.modelovanje;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Racun {
	private long id;
	private LocalDateTime datum;
	private double ukupnaCena;
	final Set<Stavka> stavke = new HashSet<>();
	
	//konstruktori
	public Racun(long id, LocalDateTime datum, double ukupnaCena) {
		super();
		this.id = id;
		this.datum = datum;
		this.ukupnaCena = ukupnaCena;
	}
	public Racun(LocalDateTime datum, double ukupnaCena) {
		this(0, datum, ukupnaCena);
	}
	public Racun() {
		this(0, LocalDateTime.now(), 0.0);
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
		Racun other = (Racun) obj;
		return id == other.id;
	}
	//zastita
	public Collection<Stavka> getStavke() {
		return Collections.unmodifiableCollection(this.stavke);
	}
	
	//metode za povezivanje
	
	public void dodajStavku(Stavka stavka) {
		this.stavke.add(stavka);
		this.ukupnaCena += stavka.getKolicina()*stavka.getProizvod().getCena();
		stavka.racun = this;
	}
	
	public void dodajSveStavke(Collection<Stavka> stavke) {
		this.stavke.addAll(stavke);
		for(Stavka s : stavke) {
			s.racun = this;
			this.ukupnaCena += s.getKolicina()*s.getProizvod().getCena();
		}
	}
	
	public void ukloniStavku(Stavka stavka) {
		stavka.racun = null;
		this.stavke.remove(stavka);
	}
	
	public void ukloniSveStavke() {
		for(Stavka s : stavke) {
			s.racun = null;
		}
		this.stavke.clear();
	}
	
	
	//getteri i setteri
	public LocalDateTime getDatum() {
		return datum;
	}
	public void setDatum(LocalDateTime datum) {
		this.datum = datum;
	}
	public double getUkupnaCena() {
		return ukupnaCena;
	}
	public void setUkupnaCena(double ukupnaCena) {
		this.ukupnaCena = ukupnaCena;
	}
	public long getId() {
		return id;
	}
	
	
	
	
	//tostring
	@Override
	public String toString() {
		return "Racun [id=" + id + ", datum=" + datum + ", ukupnaCena=" + ukupnaCena + "]";
	}
	

}
