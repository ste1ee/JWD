package com.ftninformatika.jwd.modul1.termin1.model;

import java.util.Objects;

public class SadrzajSobe {
	private long id;
	private boolean posteljina;
	private boolean klima;
	
	//konstruktori
	public SadrzajSobe(long id, boolean posteljina, boolean klima) {
		super();
		this.id = id;
		this.posteljina = posteljina;
		this.klima = klima;
	}
	public SadrzajSobe(boolean posteljina, boolean klima) {
		this(0, posteljina, klima);
	}
	public SadrzajSobe() {
		this(0, false, false);
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
		SadrzajSobe other = (SadrzajSobe) obj;
		return id == other.id;
	}
	
	//getteri i setteri
	public long getId() {
		return id;
	}
	public boolean isPosteljina() {
		return posteljina;
	}
	public void setPosteljina(boolean posteljina) {
		this.posteljina = posteljina;
	}
	public boolean isKlima() {
		return klima;
	}
	public void setKlima(boolean klima) {
		this.klima = klima;
	}
	@Override
	public String toString() {
		return "SadrzajSobe [id=" + id + ", posteljina=" + posteljina + ", klima=" + klima + "]";
	}
	
	
	

}
