package com.ftninformatika.jwd.modul1.termin6.automobili.model.impl;

import com.ftninformatika.jwd.modul1.termin6.automobili.model.ElektricniAutomobil;

public class ElektricniAutomobilImpl extends AutomobilImpl implements ElektricniAutomobil {

	private double kapacitetBaterije;
	private double domet;

	public ElektricniAutomobilImpl(String model, double snagaMotora, double kapacitetBaterije, double domet) {
		super(model, snagaMotora);
		this.kapacitetBaterije = kapacitetBaterije;
		this.domet = domet;
	}

	@Override
	public double getKapacitetBaterije() {
		return kapacitetBaterije;
	}

	@Override
	public void setKapacitetBaterije(double kapacitetBaterije) {
		this.kapacitetBaterije = kapacitetBaterije;

	}

	@Override
	public double getDomet() {
		return domet;
	}

	@Override
	public void setDomet(double domet) {
		this.domet = domet;

	}

}
