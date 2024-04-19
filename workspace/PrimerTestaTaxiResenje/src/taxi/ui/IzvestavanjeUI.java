package taxi.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.ftninformatika.jwd.modul1.util.Konzola;

import taxi.model.Poziv;
import taxi.model.StavkaIzvestaja;
import taxi.model.Vozilo;
import taxi.model.dao.VoziloDAO;

public class IzvestavanjeUI {

	private static VoziloDAO voziloDAO;

	public static void setVoziloDAO(VoziloDAO voziloDAO) {
		IzvestavanjeUI.voziloDAO = voziloDAO;
	}

	public static void izvestavanje() {
		LocalDateTime pocetak = Konzola.ocitajDateTime("Unesite pocetak perioda");
		LocalDateTime kraj = Konzola.ocitajDateTime("Unesite kraj perioda");
		try {
			Collection<Vozilo> vozila = voziloDAO.getAll();
			// pokupiti sve ulice u jednu kolekciju (Set sprečava duplikate)
			Set<String> ulice = new LinkedHashSet<>();
			for (Vozilo itVozilo: vozila) {
				for (Poziv itPoziv: itVozilo.getPozivi()) {
					ulice.add(itPoziv.getUlica());
				}
			}
			// generisanje izveštaja
			List<StavkaIzvestaja> izvestaj = new ArrayList<>();
			for (String itUlica: ulice) { // za sve ulice
				// kolona 3
				int maksPoziva = Integer.MIN_VALUE; // početni maksimum
				// kolona 2
				Vozilo voziloMaksPoziva = null; // traženi podatak koji zavisi od maksimuma

				for (Vozilo itVozilo: vozila) { // za sva vozila
					int brojPoziva = itVozilo.getPoziviIzUliceUVremenskomOpsegu(itUlica, pocetak, kraj).size();
					if (brojPoziva > maksPoziva) { // kriterijum maksimuma
						// kolona 3
						maksPoziva = brojPoziva;  // novi maksimum
						// kolona 2
						voziloMaksPoziva = itVozilo; // ažuriranje traženog podatka kada je nađen novi maksimum
					}
				}
				StavkaIzvestaja stavka = new StavkaIzvestaja(itUlica, voziloMaksPoziva, maksPoziva);
				izvestaj.add(stavka);
			}	
			// sortiranje izveštaja
			izvestaj.sort(StavkaIzvestaja::compareBrojPoziva);

			// prikaz izveštaja
			System.out.println();
			for (StavkaIzvestaja itStavka: izvestaj) {
				System.out.println(itStavka);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Došlo je do greške!");
		}
	}
	
}
