package com.ftninformatika.jwd.modul1.termin1.model;

import java.util.Objects;

public class Soba {
	private long id;
	private String tip;
	private int brojKreveta;
	private double cenaNocenja;
	SadrzajSobe sadrzajsobe;
	
	//konstruktori
	public Soba(long id,String tip, int brojKreveta, double cenaNocenja) {
		super();
		this.id = id;
		this.tip = tip;
		this.brojKreveta = brojKreveta;
		this.cenaNocenja = cenaNocenja;
	}
	public Soba(String tip,int brojKreveta, double cenaNocenja) {
		this(0, tip, brojKreveta, cenaNocenja);
	}
	public Soba() {
		this(0, "", 0, 0.0);
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
		Soba other = (Soba) obj;
		return id == other.id;
	}
	
	public void dodajSadrzaj(SadrzajSobe sadrzaj) {
		this.sadrzajsobe = sadrzaj;
	}
	public void ukloniSadrzaj() {
		this.sadrzajsobe = null;
	}
	
	//getteri i setteri
	public long getId() {
		return id;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public int getBrojKreveta() {
		return brojKreveta;
	}
	public void setBrojKreveta(int brojKreveta) {
		this.brojKreveta = brojKreveta;
	}
	public double getCenaNocenja() {
		return cenaNocenja;
	}
	public void setCenaNocenja(double cenaNocenja) {
		this.cenaNocenja = cenaNocenja;
	}
	@Override
	public String toString() {
		return "Soba [id=" + id + ", tip=" + tip + ", brojKreveta=" + brojKreveta + ", cenaNocenja=" + cenaNocenja
				+ "]";
	}
	
	
	
	

}
