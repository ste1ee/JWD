package com.ftninformatika.jwd.modul1.termin4.model;
import java.util.Objects;


public class Stavka {
   
	// nepromenljiva polja
	private long id;
	
	// promenljiva polja
	
	Proizvod proizvod;
	
	private int kolicina;
	
	// konstruktori
	public Stavka(long id, Proizvod proizvod, int kolicina) {
		this.id = id;
		this.proizvod = proizvod;
		this.kolicina = kolicina;
	}
	public Stavka(Proizvod proizvod, int kolicina) {
		this(0, proizvod, kolicina);
	}
	
	public Stavka() {
		this(0, null, 0);
	}
	// metoda za povezivanje
	
	public void addKolicinu(int dodajKolicinu) {
		this.kolicina += dodajKolicinu;
	}
	
	public void removeKolicinu(int ukloniKolicinu) {
		if(this.kolicina - ukloniKolicinu >0) {
			this.kolicina -= ukloniKolicinu;
		}
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
		if(getClass() !=obj.getClass())
			return false;
		Stavka other = (Stavka)obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		// ne ispisivati povezane entitete (zbog moguće rekurzije)
		return "Stavka [id=" + id + ", kolicina=" + kolicina+ ", proizvod=" + proizvod.getNaziv() + "]";
	}

	// getter-i i setter-i
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
	public void setId(long id) {
		this.id = id;
	} 

}
