package com.ftninformatika.jwd.modul1.termin6.geometrija.model.impl;

import com.ftninformatika.jwd.modul1.termin6.geometrija.model.GeometrijskiOblik;

public class Krug implements GeometrijskiOblik {

	private double poluprecnik;

	public Krug(double poluprecnik) {
		this.poluprecnik = poluprecnik;
	}

	@Override
	public double obim() {
		return 2 * poluprecnik * Math.PI;
	}

	@Override
	public double povrsina() {
		return poluprecnik * poluprecnik * Math.PI;
	}

	@Override
	public String toString() {
		return "Krug [poluprecnik=" + poluprecnik + "]";
	}

	public double getPoluprecnik() {
		return poluprecnik;
	}

	public void setPoluprecnik(double poluprecnik) {
		this.poluprecnik = poluprecnik;
	}

}
