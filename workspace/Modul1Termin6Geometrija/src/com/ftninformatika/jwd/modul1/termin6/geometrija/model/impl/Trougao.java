package com.ftninformatika.jwd.modul1.termin6.geometrija.model.impl;

import com.ftninformatika.jwd.modul1.termin6.geometrija.model.Poligon;

public class Trougao implements Poligon {

	private double stranicaA;
	private double stranicaB;
	private double stranicaC;

	public Trougao(double stranicaA, double stranicaB, double stranicaC) {
		this.stranicaA = stranicaA;
		this.stranicaB = stranicaB;
		this.stranicaC = stranicaC;
	}

	@Override
	public double obim() {
		return stranicaA + stranicaB + stranicaC;
	}

	@Override
	public double povrsina() {
		double s = (stranicaA + stranicaB + stranicaC) / 2;
		return Math.sqrt(s * (s - stranicaA) * (s - stranicaB) * (s - stranicaC));
	}

	@Override
	public int brojStranica() {
		return 3;
	}

	@Override
	public String toString() {
		return "Trougao [stranicaA=" + stranicaA + ", stranicaB=" + stranicaB + ", stranicaC=" + stranicaC + "]";
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

	public double getStranicaC() {
		return stranicaC;
	}

	public void setStranicaC(double stranicaC) {
		this.stranicaC = stranicaC;
	}

}
