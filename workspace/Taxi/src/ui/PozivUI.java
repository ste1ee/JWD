package ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import dao.PozivDAO;
import dao.VoziloDAO;
import model.Izvestaj;
import model.Poziv;
import model.Vozilo;
import util.Tabela;
import util.Konzola;

public class PozivUI {
	
	private static PozivDAO pozivDAO;
	//private static VoziloDAO voziloDAO;
	
	public static void setPozivDAO(PozivDAO pozivDAO) {
		PozivUI.pozivDAO = pozivDAO;
	}
	
	//public static void setVoziloDAO(VoziloDAO voziloDAO) {
	//	PozivUI.voziloDAO = voziloDAO;
	//}
	
	private static final Tabela<Poziv> TABELA = new Tabela<>("%5s %30s %-50s %5s %5s",
			new Object[] { "ID", "Datum i vreme", "Ulica", "Broj", "Vozilo" }) {

		@Override
		protected List<Object[]> uredi(Poziv vrednost) {
			List<Object[]> rezultat = new ArrayList<>();
			rezultat.add(new Object[] { vrednost.getId(), Konzola.formatiraj(vrednost.getDatumIVreme()), vrednost.getUlica(), vrednost.getBroj(),
					(vrednost.getVozilo() != null) ? vrednost.getVozilo().getBroj() : ""});
			return rezultat;
		}

	};
	
	public static Poziv pronalazenje() throws Exception {
		prikazSvih();

		long id = Konzola.ocitajLong("Unesite ID poziva");

		Poziv poziv = pozivDAO.get(id);
		if (poziv == null)
			Konzola.prikazi("Poziv nije pronadjen!");

		return poziv;
	}
	
	private static void prikazSvih() {
		try {
			Collection<Poziv> pozivi = pozivDAO.getAll();
			TABELA.prikazi(pozivi);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske!");
		}
	}
	
	private static void prikaz() {
		try {
			Poziv poziv = pronalazenje();
			if (poziv == null)
				return;

			TABELA.prikazi(poziv);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Doslo je do greske!");
		}
	}
	
	private static void dodavanje() {
		// kreiranje
		LocalDateTime datumIVreme = LocalDateTime.now();
		
		int brojVozila = 0;
		while(brojVozila == 0) {
			brojVozila = Konzola.ocitajInt("Unesite broj vozila");
			Map<Vozilo> vozila = Izvestaj.getVozila();
		}


		String ulica = "";
		while (ulica.equals("")){
			ulica = Konzola.ocitajString("Unesite ulicu");
		}
		int broj = 0;
		while (broj == 0 )
			broj = Konzola.ocitajInt("Unesite broj");
		
		Poziv poziv = new Poziv(datumIVreme, ulica, broj, vozilo);
		try {
			// Ä�uvanje
			projekcijaDAO.add(projekcija);
			Konzola.prikazi("UspeÅ¡no dodavanje!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("DoÅ¡lo je do greÅ¡ke!");
		}
	}
	
}
