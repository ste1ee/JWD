package com.ftninformatika.jwd.modul1.termin6.prodavnica.ui;

import com.ftninformatika.jwd.modul1.termin6.prodavnica.model.PrehrambeniProizvod;
import com.ftninformatika.jwd.modul1.termin6.prodavnica.model.Proizvod;
import com.ftninformatika.jwd.modul1.termin6.prodavnica.model.Racun;
import com.ftninformatika.jwd.modul1.termin6.prodavnica.model.TehnickiProizvod;

public class ProdavnicaUI {

	public static void main(String[] args) {
		Proizvod[] proizvodi = new Proizvod[] {
				new PrehrambeniProizvod("0001", "Hleb 700g", 50.0, 7), 
				new PrehrambeniProizvod("0002", "Mleko 1.5l", 150.0, 7), 
				new TehnickiProizvod("0003", "Samsung HDTV 43\"", 40000.0, 24)
		};
		Racun racun = new Racun(proizvodi);
		
		for (Proizvod itProizvod: proizvodi) {
			System.out.println(itProizvod);
		}
		System.out.println();
		System.out.println(racun);
	}

}
