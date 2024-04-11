package com.ftninformatika.jwd.modul1.termin6.geometrija.model.impl;

public class Pravougaonik extends Cetvorougao {

	private double stranicaA;
	private double stranicaB;

	public Pravougaonik(double stranicaA, double stranicaB) {
		this.stranicaA = stranicaA;
		this.stranicaB = stranicaB;
	}

	@Override
	public double obim() {
		return 2 * (stranicaA + stranicaB);
	}

	@Override
	public double povrsina() {
		return stranicaA * stranicaB;
	}

	@Override
	public String toString() {
		return "Pravougaonik [stranicaA=" + stranicaA + ", stranicaB=" + stranicaB + "]";
	}

	public double getStranicaA() {
		return stranicaA;
	}

	public void setStranicaA(double stranicaA) {
		this.stranicaA = stranicaA;
	}

	public double getStranicaB() {
		return stranicaB;
	}

	public void setStranicaB(double stranicaB) {
		this.stranicaB = stranicaB;
	}

}
