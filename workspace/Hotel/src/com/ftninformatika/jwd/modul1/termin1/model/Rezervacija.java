package com.ftninformatika.jwd.modul1.termin1.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Rezervacija {
	private long id;
	private LocalDateTime datumIVremeUlaska;
	private String imeIPrezimeGosta;
	
	final Set<Soba> sobe = new HashSet<>();
	
	//konstruktori
	public Rezervacija(long id, LocalDateTime datumIVremeUlaska, String imeIPrezimeGosta) {
		super();
		this.id = id;
		this.datumIVremeUlaska = datumIVremeUlaska;
		this.imeIPrezimeGosta = imeIPrezimeGosta;
	}
	public Rezervacija(LocalDateTime datumIVremeUlaska, String imeIPrezimeGosta) {
		this(0, datumIVremeUlaska, imeIPrezimeGosta);
	}
	public Rezervacija() {
		this(0, null, "");
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
		Rezervacija other = (Rezervacija) obj;
		return id == other.id;
	}
	
	public Collection<Soba> getSobe() {
		return Collections.unmodifiableCollection(this.sobe);
	}
	
	public void dodajSobu(Soba soba) {
		this.sobe.add(soba);
	}
	
	public void dodajSveSobe(Collection<Soba> sobe) {
		this.sobe.addAll(sobe);
	}
	
	public void ukloniSobu(Soba soba) {
		this.sobe.remove(soba);
	}
	
	public void ukloniSveSobe() {
		this.sobe.clear();
	}
	
	//getteri i setteri
	public long getId() {
		return id;
	}
	
	public LocalDateTime getDatumIVremeUlaska() {
		return datumIVremeUlaska;
	}
	
	public void setDatumIVremeUlaska(LocalDateTime datumIVremeUlaska) {
		this.datumIVremeUlaska = datumIVremeUlaska;
	}

	public String getImeIPrezimeGosta() {
		return imeIPrezimeGosta;
	}
	public void setImeIPrezimeGosta(String imeIPrezimeGosta) {
		this.imeIPrezimeGosta = imeIPrezimeGosta;
	}
	@Override
	public String toString() {
		return "Rezervacija [id=" + id + ", datumIVremeUlaska=" + datumIVremeUlaska + ", imeIPrezimeGosta="
				+ imeIPrezimeGosta + "]";
	}
	
	
	
	

}
