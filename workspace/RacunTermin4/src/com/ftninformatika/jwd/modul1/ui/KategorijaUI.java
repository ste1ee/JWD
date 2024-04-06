package com.ftninformatika.jwd.modul1.ui;

import java.io.IOException;
import java.util.Map;

import com.ftninformatika.jwd.modul1.uti.model.Kategorija;
import com.ftninformatika.jwd.modul1.uti.model.Prodavnica;
import com.ftninformatika.jwd.modul1.util.Konzola;
import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;

public class KategorijaUI {
	
	public static Kategorija pronalazenje() {
		prikazSvih();
		long id = Konzola.ocitajLong("Unesite ID kategorije");
		Map<Long, Kategorija> kategorije = Prodavnica.getKategorije();
		Kategorija kategorija = kategorije.get(id);
		if(kategorija == null) {
			Konzola.prikazi("Nepostojeca kategorija!");
		}
		return kategorija;
	}

	static void prikazSvih() {
		Map<Long, Kategorija> kategorije = Prodavnica.getKategorije();
		for(Kategorija k : kategorije.values()) {
			System.out.println(k);;
		}
	}
	
	private static void prikaz() {
		Kategorija kategorija = pronalazenje();
		if(kategorija == null) {
			return;
		}
		System.out.println(kategorija);;
	}
	
	private static void dodavanje() {
		long id = Prodavnica.nextKategorijaId();
		String naziv = "";
		while(naziv.equals("")) {
			naziv = Konzola.ocitajString("Unesi naziv:");
		}
		Kategorija kategorija = new Kategorija(id, naziv);
		
		Map<Long, Kategorija> kategorije = Prodavnica.getKategorije();
		kategorije.put(kategorija.getId(), kategorija);
		
		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspesno dodato!");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	private static void izmena() {
		Kategorija kategorija = pronalazenje();
		if(kategorija == null) {
			return;
		}
		String naziv = "";
		while(naziv.equals("")) {
			naziv = Konzola.ocitajString("Unesi naziv:");
		}
		kategorija.setNaziv(naziv);
		
		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspesno izmenjeno!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void brisanje() {
		Kategorija kategorija = pronalazenje();
		if(kategorija == null) {
			return;
		}
		Map<Long, Kategorija> kategorije = Prodavnica.getKategorije();
		kategorije.remove(kategorija.getId());
		
		try {
			Prodavnica.sacuvaj();
			Konzola.prikazi("Uspesno izbrisano!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void meni() {
		Meni.pokreni("Kategorije", new StavkaMenija[] {
				new IzlaznaStavkaMenija("Povratak"),
				new FunkcionalnaStavkaMenija("Prikazi sve") {
					@Override
					public void izvrsi() {
						prikazSvih();
					}
				},
				new FunkcionalnaStavkaMenija("Prikazi") {
					@Override
					public void izvrsi() {
						prikaz();
					}
				},
				new FunkcionalnaStavkaMenija("Dodaj") {
					
					@Override
					public void izvrsi() {
						dodavanje();
					}
				},
				new FunkcionalnaStavkaMenija("Izmeni") {
					
					@Override
					public void izvrsi() {
						izmena();
						
					}
				},
				new FunkcionalnaStavkaMenija("Izbrisi") {
					
					@Override
					public void izvrsi() {
						brisanje();
						
					}
				}	
		});
		
		
		
		
		
		
	}
	
	

}
