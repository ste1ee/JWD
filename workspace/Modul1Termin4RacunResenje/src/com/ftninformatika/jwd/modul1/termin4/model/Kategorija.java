package com.ftninformatika.jwd.modul1.termin4.model;

import java.util.Objects;

public class Kategorija {
	// nepromenljiva polja
	private long id;
	
	// promenljiva polja
	private String naziv;
	
	// konstruktori
	public Kategorija(long id, String naziv) {
		this.id = id;
		this.naziv = naziv;
	}
	public Kategorija(String naziv) {
		this(0, naziv);
	}
	
	public Kategorija() {
		this(0,"");
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
		Kategorija other = (Kategorija)obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		// ne ispisivati povezane entitete (zbog moguće rekurzije)
		return "Kategorija [id=" + id + ", naziv=" + naziv + "]";
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
	public void setId(long id) {
		this.id = id;
	} 

}