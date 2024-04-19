package ui;

import java.util.Collection;

import dao.VozDAO;
import model.Karta;
import model.Voz;
import util.Konzola;

public class VozUI {

	private static VozDAO vozDAO;

	public static void setVozDAO(VozDAO vozDAO) {
		VozUI.vozDAO = vozDAO;
	}

	public static Voz pronalazenje() throws Exception {
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite id voza");

		Voz voz = vozDAO.get(id);
		if (voz == null)
			Konzola.prikazi("Voz nije pronadjen!");

		return voz;
	}

	public static void prikazSvih() {
		try {
			Collection<Voz> vozovi = vozDAO.getAll();

			System.out.println();
			for (Voz itVoz : vozovi) {
				System.out.println(itVoz);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske!");
		}
	}

	public static void prikaz() {
		try {
			Voz voz = pronalazenje();
			if (voz == null) {
				return;
			}

			System.out.println();
			System.out.println(voz);
			for (Karta itKarta : voz.getKarte()) {
				System.out.println(itKarta);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske!");
		}
	}

}
