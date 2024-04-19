package taxi.ui;

import java.time.LocalDateTime;

import com.ftninformatika.jwd.modul1.util.Konzola;

import taxi.model.Poziv;
import taxi.model.Vozilo;
import taxi.model.dao.PozivDAO;

public class PozivUI {

	private static PozivDAO pozivDAO;

	public static void setPozivDAO(PozivDAO pozivDAO) {
		PozivUI.pozivDAO = pozivDAO;
	}

	public static void dodavanje() {
		try {
			Vozilo vozilo = VozilaUI.pronalazenje();
			if (vozilo == null) {
				return;
			}
			String ulica = Konzola.ocitajString("Unesite ulicu");
			int broj = Konzola.ocitajInt("Unesite broj");

			LocalDateTime datumIVreme = LocalDateTime.now();
			Poziv poziv = new Poziv(0, datumIVreme, ulica, broj, vozilo);
			pozivDAO.add(poziv);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

}
