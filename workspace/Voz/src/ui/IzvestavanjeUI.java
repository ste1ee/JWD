package ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import dao.VozDAO;
import model.Karta;
import model.Voz;
import util.Konzola;

public class IzvestavanjeUI {
	
	private static VozDAO vozDAO;
	public static void setVozDAO(VozDAO vozDAO) {
		IzvestavanjeUI.vozDAO = vozDAO;
	}
	
	public static void izvestavanje() {
		//LocalDateTime pocetak = Konzola.ocitajDateTime("Unesite pocetno vreme");
		//LocalDateTime kraj = Konzola.ocitajDateTime("Unesite krajnje vreme");
		
		try {
			Collection<Voz> vozoviColl = vozDAO.getAll();
			Map<String, Voz> vozovi = new LinkedHashMap<>();
			for(Voz v : vozoviColl) {
				if(vozovi.containsKey(v.getNaziv())) {
					for(Karta k : v.getKarte()) {
						vozovi.get(v.getNaziv()).addKarta(k);
					}
				}
				if(!vozovi.containsKey(v.getNaziv()))
					vozovi.put(v.getNaziv(), v);
			}
			for(Voz v : vozovi.values()) {
				System.out.print(v.getNaziv());
				double prihodBP = 0;
				double prihod = 0;
				double popust = 0;
				for(Karta k : v.getKarte()) {
					if(k.getRazred() == 2) {
						popust += (v.getCenaKarte()*15)/100;
					}
				}
				prihodBP += v.getCenaKarte() * v.getKarte().size();
				//System.out.print(" " + prihodBP);
				//System.out.println(" " + popust);
				prihod = prihodBP - popust;
				System.out.println(" " + prihod);
				//System.out.print(" " + v.getKarte().size());
				System.out.println();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
