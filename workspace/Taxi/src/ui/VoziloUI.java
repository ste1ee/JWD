package ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dao.VoziloDAO;
import model.Poziv;
import model.Vozilo;
import util.Konzola;
import util.Tabela;

public class VoziloUI {
	
	private static VoziloDAO voziloDAO;
	
	public static void setVoziloDAO(VoziloDAO voziloDAO) {
		VoziloUI.voziloDAO = voziloDAO;
	}
	
	private static final Tabela<Vozilo> TABELA = new Tabela<>(
			"%2s %20s %-50s",
			new Object[] {"ID", "Broj", "Vozac"}
		) {

			@Override
			protected List<Object[]> uredi(Vozilo vrednost) {
				List<Object[]> rezultat = new ArrayList<>();
				rezultat.add(new Object[] { vrednost.getId(), vrednost.getBroj(), vrednost.getVozac() });
				return rezultat;
			}

	};
	
	private static final Tabela<Vozilo> TABELA2 = new Tabela<>(
			"%2s %20s %-50s %-20s",
			new Object[] {"ID", "Broj", "Vozac", "Pozivi"}
		) {

			@Override
			protected List<Object[]> uredi(Vozilo vrednost) {
				List<Object[]> rezultat = new ArrayList<>();
				rezultat.add(new Object[] { vrednost.getId(), vrednost.getBroj(), vrednost.getVozac(), "" });
				for (Poziv itPoziv: vrednost.getPozivi()) // many-to-many veza
					rezultat.add(new Object[] { "", "", "", itPoziv.getId() });
				return rezultat;
			}
	
	};
	
	public static Vozilo pronalazenje() throws Exception {
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite ID vozila");

		Vozilo vozilo = voziloDAO.get(id);
		if (vozilo == null)
			Konzola.prikazi("Vozilo nije pronadjeno!");

		return vozilo;
	}
	
	protected static void prikazSvih() {
		try {
			Collection<Vozilo> vozila = voziloDAO.getAll();
			TABELA.prikazi(vozila);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

	protected static void prikaz() {
		try {
			Vozilo vozilo = pronalazenje();
			if (vozilo == null)
				return;
			
			TABELA2.prikazi(vozilo);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}

}
