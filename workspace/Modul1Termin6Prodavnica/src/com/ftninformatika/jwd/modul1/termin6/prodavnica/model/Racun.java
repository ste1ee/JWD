package com.ftninformatika.jwd.modul1.termin6.prodavnica.model;

import java.util.Arrays;

public class Racun {
	// nepromenljiva polja
	private final Proizvod[] proizvodi;

	// konstruktori
	public Racun(Proizvod[] proizvodi) {
		this.proizvodi = proizvodi;
	}

	// metode
	public double cena() {
		double cena = 0;
		for (Proizvod itProizvod: proizvodi) {
			cena += itProizvod.getCena();
		}
		return cena;
	}

	public double cenaPDV() {
		double cena = 0;
		for (Proizvod itProizvod: proizvodi) {
			cena += itProizvod.cenaPDV();
		}
		return cena;
	}

	// Object metode
	@Override
	public String toString() {
		return "Racun [cena=" + cena() + ", cenaPDV=" + cenaPDV() + ", proizvodi=" + Arrays.toString(proizvodi) + "]";
	}
	
	// getter-i i setter-i
	public Proizvod[] getProizvodi() {
		return proizvodi;
	}

}
