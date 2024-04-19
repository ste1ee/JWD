package ui;

import java.time.LocalDateTime;

import dao.KartaDAO;
import model.Karta;
import model.Voz;
import util.Konzola;

public class KartaUI {

	private static KartaDAO kartaDAO;

	public static void setKartaDAO(KartaDAO kartaDAO) {
		KartaUI.kartaDAO = kartaDAO;
	}

	public static void dodavanje() {
		try {
			Voz voz = VozUI.pronalazenje();
			if (voz == null) {
				return;
			}
			int razred = Konzola.ocitajInt("Unesite razred");
			if (razred != 1 && razred != 2) {
				return;
			}
			String kupac = Konzola.ocitajString("Unesite kupca");

			LocalDateTime dIVProdaje = LocalDateTime.now();
			Karta karta = new Karta(0, voz, dIVProdaje, kupac, razred);
			kartaDAO.add(karta);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske!");
		}
	}

}
