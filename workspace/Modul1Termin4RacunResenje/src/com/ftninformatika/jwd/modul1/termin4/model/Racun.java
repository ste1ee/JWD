package com.ftninformatika.jwd.modul1.termin4.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



public class Racun {
	// nepromenljiva polja
	private long id;
			
	// promenljiva polja
	private LocalDateTime datum; // promeni se na svaku izmenu racuna
	
	final Set<Stavka> stavke = new HashSet<Stavka>();
	
	private double ukupnaCena;

	// konstruktori
	public Racun(long id,LocalDateTime datum,  double ukupnaCena) {
		this.id = id;
		this.datum = datum;
		this.ukupnaCena = ukupnaCena;
	}
	public Racun(LocalDateTime datumKreiranja) {
		this(0,datumKreiranja,  0.0);
	}
	
	public Racun() {
		this(0,null, 0.0);
	}
	// metoda za povezivanje
	
	public Collection<Stavka> getStavke(){
		return Collections.unmodifiableCollection(this.stavke);
	}
    
	public void addStavku(Stavka stavka) {
		boolean flag = true;
		// prolazimo kroz racun
		// i ukoliko neka stavka postoji u racunu
		// mi zelimo da povecamo samo kvantitet
		// i izracunamo ukupnu cenu
		String nazivProizvoda = stavka.proizvod.getNaziv();
		for(Stavka itStavka: this.stavke) {
			if(itStavka.proizvod.getNaziv().equals(nazivProizvoda)){
				flag = false;
			}
		}
		if(flag)
			this.stavke.add(stavka);
		
		this.ukupnaCena += stavka.getKolicina()*stavka.getProizvod().getCena();
	}
	
	public void addAllStavke(Collection<Stavka> stavke) {
	
		this.stavke.addAll(stavke);
		for(Stavka s: stavke) {
			//nemamo vezu sa suprotne strane i nije potrebno ulancavanje
			this.ukupnaCena += s.getKolicina()*s.getProizvod().getCena();
		}
	}
	
	public void removeStavku(Stavka stavka) {
		//nemamo vezu sa suprotne strane i nije potrebno razvezivanje
		this.stavke.remove(stavka); 	
		this.ukupnaCena -= stavka.getKolicina()*stavka.getProizvod().getCena();
	}
	
	 public void removeAllStavke() {
		//nemamo vezu sa suprotne strane i nije potrebno razvezivanje
		 this.stavke.clear();
		 this.ukupnaCena = 0.0;
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
		 Racun other = (Racun) obj;
		 return id == other.id;
	 }
	   
	@Override
	public String toString() {
		// ne ispisivati povezane entitete (zbog moguće rekurzije)
		return "Racun [id=" + id + ", datum=" + Prodavnica.DATE_TIME_FORMATTER.format(datum)  +", ukupna cena =" + ukupnaCena  + "]";
	}

	// getter-i i setter-i
	public long getId() {
		return id;
	}
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
	public void setId(long id) {
		this.id = id;
	} 	
	
}