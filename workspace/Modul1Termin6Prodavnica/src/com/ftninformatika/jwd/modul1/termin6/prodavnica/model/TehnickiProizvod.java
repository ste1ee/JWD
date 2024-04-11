package com.ftninformatika.jwd.modul1.termin6.prodavnica.model;

public class TehnickiProizvod extends Proizvod {
	// promenljiva polja
	private int garancija;

	// konstruktori
	public TehnickiProizvod(String sifra, String naziv, double cena, 
			int garancija) {
		super(sifra, naziv, cena);

		this.garancija = garancija;
	}

	// metode
	@Override
	public double cenaPDV() {
		return 1.2*cena;
	}
	
	// Object metode
	@Override
	public String toString() {
		return "TehnickiProizvod [sifra=" + sifra + ", naziv=" + naziv + ", cena=" + cena + ", cenaPDV=" + cenaPDV() + 
				", garancija=" + garancija + "]";
	}

	// getter-i i setter-i
	public int getGarancija() {
		return garancija;
	}

	public void setGarancija(int garancija) {
		this.garancija = garancija;
	}
	
}
