package com.ftninformatika.jwd.modul1.termin6.prodavnica.model;

public class PrehrambeniProizvod extends Proizvod {
	// promenljiva polja
	private int rokTrajanja;

	// konstruktori
	public PrehrambeniProizvod(String sifra, String naziv, double cena, 
			int rokTrajanja) {
		super(sifra, naziv, cena);

		this.rokTrajanja = rokTrajanja;
	}

	// metode
	@Override
	public double cenaPDV() {
		return 1.1*cena;
	}
	
	// Object metode
	@Override
	public String toString() {
		return "PrehrambeniProizvod [sifra=" + sifra + ", naziv=" + naziv + ", cena=" + cena + ", cenaPDV=" + cenaPDV() + 
				", rokTrajanja=" + rokTrajanja + "]";
	}

	// getter-i i setter-i
	public int getRokTrajanja() {
		return rokTrajanja;
	}

	public void setRokTrajanja(int rokTrajanja) {
		this.rokTrajanja = rokTrajanja;
	}

}
