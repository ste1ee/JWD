package taxi.ui;

import java.util.Collection;

import com.ftninformatika.jwd.modul1.util.Konzola;

import taxi.model.Poziv;
import taxi.model.Vozilo;
import taxi.model.dao.VoziloDAO;

public class VozilaUI {

	private static VoziloDAO voziloDAO;

	public static void setVoziloDAO(VoziloDAO voziloDAO) {
		VozilaUI.voziloDAO = voziloDAO;
	}

	public static Vozilo pronalazenje() throws Exception {
		prikazSvih();

		String broj = Konzola.ocitajString("Unesite broj vozila");

		Vozilo film = voziloDAO.get(broj);
		if (film == null)
			Konzola.prikazi("Vozilo nije pronađeno!");

		return film;
	}

	public static void prikazSvih() {
		try {
			Collection<Vozilo> vozila = voziloDAO.getAll();

			System.out.println();
			for (Vozilo itVozilo: vozila) {
				System.out.println(itVozilo);
				for (Poziv itPoziv: itVozilo.getPozivi()) {
					System.out.println(itPoziv);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	public static void prikaz() {
		try {
			Vozilo vozilo = pronalazenje();
			if (vozilo == null) {
				return;
			}

			System.out.println();
			System.out.println(vozilo);
			for (Poziv itPoziv : vozilo.getPozivi()) {
				System.out.println(itPoziv);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

}
