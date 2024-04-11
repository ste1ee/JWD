package com.ftninformatika.jwd.modul1.termin6.geometrija.model.impl;

public class Kvadrat extends Cetvorougao {
	// promenljiva polja
	private double stranica;

	// konstruktori
	public Kvadrat(double stranica) {
		this.stranica = stranica;
	}

	// metode
	@Override
	public double obim() {
		return 4 * stranica;
	}

	@Override
	public double povrsina() {
		return stranica * stranica;
	}

	@Override
	public String toString() {
		return String.format("%s [obim=%.2f, povrsina=%.2f]", naziv(), obim(), povrsina());
	}

	// getter-i i setter-i
	public double getStranica() {
		return stranica;
	}

	public void setStranica(double stranica) {
		this.stranica = stranica;
	}

}
