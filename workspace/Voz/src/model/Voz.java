package model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Voz {

	private long id;
	private String broj;
	private String naziv;
	private LocalDateTime dIVPolaska;
	private double cenaKarte;
	private int brojMesta;
	private double prihod;

	public double getPrihod() {
		return prihod;
	}

	public void setPrihod(double prihod) {
		this.prihod = prihod;
	}

	Set<Karta> karte = new LinkedHashSet<>();

	public Voz(long id, String broj, String naziv, LocalDateTime dIVPolaska, double cenaKarte, int brojMesta) {
		this.id = id;
		this.broj = broj;
		this.naziv = naziv;
		this.dIVPolaska = dIVPolaska;
		this.cenaKarte = cenaKarte;
		this.brojMesta = brojMesta;
	}

	@Override
	public String toString() {
		return "Voz [id=" + id + ", broj=" + broj + ", naziv=" + naziv + ", dIVPolaska=" + dIVPolaska + ", cenaKarte="
				+ cenaKarte + ", brojMesta=" + brojMesta + "]";
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
		Voz other = (Voz) obj;
		return id == other.id;
	}

	public Set<Karta> getKarte() {
		return Collections.unmodifiableSet(karte);
	}

	public void addKarta(Karta karta) {
		this.karte.add(karta);
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public LocalDateTime getdIVPolaska() {
		return dIVPolaska;
	}

	public void setdIVPolaska(LocalDateTime dIVPolaska) {
		this.dIVPolaska = dIVPolaska;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public long getId() {
		return id;
	}

}
