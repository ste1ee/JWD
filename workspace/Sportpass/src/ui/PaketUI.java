package ui;

import dao.PaketDAO;
import model.Paket;
import util.Konzola;

public class PaketUI {

	private static PaketDAO paketDAO;

	public static void setPaketDAO(PaketDAO paketDAO) {
		PaketUI.paketDAO = paketDAO;
	}

	public static Paket pronalazenje() {
		long id = Konzola.ocitajLong("Unesite ID paketa");
		Paket paket = null;
		try {
			paket = paketDAO.get(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paket;
	}

	public static void prikaz() {
		try {
			long id = Konzola.ocitajLong("Unesite ID paketa");
			Paket paket = paketDAO.get(id);
			if (paket == null) {
				return;
			}

			System.out.println();
			System.out.println(paket);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske!");
		}
	}

}
