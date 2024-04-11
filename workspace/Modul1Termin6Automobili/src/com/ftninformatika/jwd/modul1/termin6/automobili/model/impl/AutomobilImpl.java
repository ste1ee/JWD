package com.ftninformatika.jwd.modul1.termin6.automobili.model.impl;

import com.ftninformatika.jwd.modul1.termin6.automobili.model.Automobil;

public abstract class AutomobilImpl implements Automobil {
	// promenljiva polja
	protected String model;
	protected double snagaMotora;

	// konstruktori
	public AutomobilImpl(String model, double snagaMotora) {
		this.model = model;
		this.snagaMotora = snagaMotora;
	}

	// metode
	@Override
	public String getModel() {
		return model;
	}

	@Override
	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public double getSnagaMotora() {
		return snagaMotora;
	}

	@Override
	public void setSnagaMotora(double snagaMotora) {
		this.snagaMotora = snagaMotora;
	}

}
