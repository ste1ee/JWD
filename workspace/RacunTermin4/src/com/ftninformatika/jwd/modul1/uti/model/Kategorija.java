package com.ftninformatika.jwd.modul1.uti.model;

import java.util.Objects;

public class Kategorija {
	private long id;
	private String naziv;
	
	
	public Kategorija(long id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}
	
	public Kategorija() {
		super();
		this.id = 0;
		this.naziv = "";
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
		Kategorija other = (Kategorija) obj;
		return id == other.id;
	}
	
	

	@Override
	public String toString() {
		return "Kategorija [id=" + id + ", naziv=" + naziv + "]";
	}

	public long getId() {
		return id;
	}


	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	
	


}
