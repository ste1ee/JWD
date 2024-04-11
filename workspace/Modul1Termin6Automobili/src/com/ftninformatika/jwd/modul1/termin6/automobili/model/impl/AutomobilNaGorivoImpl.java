package com.ftninformatika.jwd.modul1.termin6.automobili.model.impl;

import com.ftninformatika.jwd.modul1.termin6.automobili.model.AutomobilNaGorivo;

public class AutomobilNaGorivoImpl extends AutomobilImpl implements AutomobilNaGorivo {
	// promenljiva polja
	protected double kapacitetRezervoara;
	protected String tipGoriva;

	// konstruktori
	public AutomobilNaGorivoImpl(String model, double snagaMotora, double kapacitetRezervoara, String tipGoriva) {
		super(model, snagaMotora);

		this.kapacitetRezervoara = kapacitetRezervoara;
		this.tipGoriva = tipGoriva;
	}

	// metode
	@Override
	public String toString() {
		return String.format("[model=%s, snagaMotora=%.2f, kapacitetRezervoara=%.2f, tipGoriva=%s]", model, snagaMotora,
				kapacitetRezervoara, tipGoriva);
	}

	@Override
	public double getKapacitetRezervoara() {
		return kapacitetRezervoara;
	}

	@Override
	public void setKapacitetRezervoara(double kapacitetRezervoara) {
		this.kapacitetRezervoara = kapacitetRezervoara;
	}

	@Override
	public String getTipGoriva() {
		return tipGoriva;
	}

	@Override
	public void setTipGoriva(String tipGoriva) {
		this.tipGoriva = tipGoriva;
	}

}
