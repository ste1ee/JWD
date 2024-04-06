package com.ftninformatika.jwd.modul1.uti.model;

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

	public Racun(long id, LocalDateTime datum, double ukupnaCena) {
		super();
		this.id = id;
		this.datum = datum;
		this.ukupnaCena = ukupnaCena;
	}

	public Racun() {
		super();
		this.id = 0;
		this.datum = LocalDateTime.now();
		this.ukupnaCena = 0.0;
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
		return "Racun [id=" + id + ", datum=" + datum + ", ukupnaCena=" + ukupnaCena + "]";
	}

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

	public Collection<Stavka> getStavke() {
		return Collections.unmodifiableCollection(stavke);
	}

	public void addStavka(Stavka stavka) {
		this.stavke.add(stavka);
		this.ukupnaCena += stavka.getKolicina() * stavka.getProizvod().getCena();
	}

	public void addAllStavke(Collection<Stavka> stavke) {
		this.stavke.addAll(stavke);
		for (Stavka s : stavke) {
			this.ukupnaCena += s.getKolicina() * s.getProizvod().getCena();
		}
	}

	public void removeStavka(Stavka stavka) {
		this.stavke.remove(stavka);
		this.ukupnaCena -= stavka.getKolicina() * stavka.getProizvod().getCena();
	}

	public void removeAllStavke() {
		this.stavke.clear();
		this.ukupnaCena = 0.0;
	}

}
