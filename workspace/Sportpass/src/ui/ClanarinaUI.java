package ui;

import java.time.LocalDate;
import java.util.Collection;

import dao.ClanarinaDAO;
import model.Clanarina;
import model.Paket;
import util.Konzola;

public class ClanarinaUI {

	private static ClanarinaDAO clanarinaDAO;

	public static void setClanarinaDAO(ClanarinaDAO clanarinaDAO) {
		ClanarinaUI.clanarinaDAO = clanarinaDAO;
	}

	public static void prikazSvih() {
		try {
			Collection<Clanarina> clanarine = clanarinaDAO.getAll();

			System.out.println();
			for (Clanarina itC : clanarine) {
				System.out.println(itC);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske!");
		}
	}

	public static void dodavanje() {
		try {
			Paket paket = PaketUI.pronalazenje();
			if (paket == null) {
				return;
			}
			String korisnickoIme = Konzola.ocitajString("Unesite korisnicko ime");
			LocalDate pocetak = LocalDate.now();

			Clanarina clanarina = new Clanarina(0, paket, korisnickoIme, pocetak);
			clanarinaDAO.add(clanarina);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske!");
		}
	}

}
