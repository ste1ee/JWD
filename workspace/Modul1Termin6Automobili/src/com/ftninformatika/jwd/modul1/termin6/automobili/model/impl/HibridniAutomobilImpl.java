package com.ftninformatika.jwd.modul1.termin6.automobili.model.impl;

import com.ftninformatika.jwd.modul1.termin6.automobili.model.HibridniAutomobil;

public class HibridniAutomobilImpl extends AutomobilImpl implements HibridniAutomobil {

	private double kapacitetRezervoara;
	private String tipGoriva;

	private double kapacitetBaterije;
	private double domet;

	public HibridniAutomobilImpl(String model, double snagaMotora, double kapacitetRezervoara, String tipGoriva,
			double kapacitetBaterije, double domet) {
		super(model, snagaMotora);
		this.kapacitetRezervoara = kapacitetRezervoara;
		this.tipGoriva = tipGoriva;
		this.kapacitetBaterije = kapacitetBaterije;
		this.domet = domet;
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
