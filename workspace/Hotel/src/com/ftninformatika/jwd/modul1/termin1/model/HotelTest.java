package com.ftninformatika.jwd.modul1.termin1.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class HotelTest {
	
	private static final String DATE_TIME_FORMAT = "dd.MM.yyyy. HH:mm";
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
	
	public static String datumUString(LocalDateTime datum) {
		return DATE_TIME_FORMATTER.format(datum);
	}
	
	public static LocalDateTime stringUDatum(String datum) {
		return LocalDateTime.parse(datum, DATE_TIME_FORMATTER);
	}
	
	public static final Set<Prenociste> prenocista = new HashSet<>();
	public static final Set<SadrzajSobe> sadrzajiSoba = new HashSet<>();
	public static final Set<Soba> sobe = new HashSet<>();
	public static final Set<Rezervacija> rezervacije = new HashSet<>();
	
	
	public static void inicijalizacija() {
		Prenociste prenociste1 = new Prenociste(1, 4999.99, 3);
		Prenociste prenociste2 = new Prenociste(2, 8999.99, 4);
		Prenociste prenociste3 = new Prenociste(3, 3499.99, 2);
		Prenociste prenociste4 = new Prenociste(4, 12499.99, 5);
		Prenociste prenociste5 = new Prenociste();
		
		prenocista.add(prenociste1);
		prenocista.add(prenociste2);
		prenocista.add(prenociste3);
		prenocista.add(prenociste4);
		prenocista.add(prenociste5);
		
		SadrzajSobe sadrzajSobe1 = new SadrzajSobe(1, true, true);
		SadrzajSobe sadrzajSobe2 = new SadrzajSobe(2, true, false);
		SadrzajSobe sadrzajSobe3 = new SadrzajSobe(3, false, true);
		SadrzajSobe sadrzajSobe4 = new SadrzajSobe(4, false, false);
		SadrzajSobe sadrzajSobe5 = new SadrzajSobe();
		
		sadrzajiSoba.add(sadrzajSobe1);
		sadrzajiSoba.add(sadrzajSobe2);
		sadrzajiSoba.add(sadrzajSobe3);
		sadrzajiSoba.add(sadrzajSobe4);
		sadrzajiSoba.add(sadrzajSobe5);
		
		Soba soba1 = new Soba(1, "jednosobna", 2, 4999.99);
		Soba soba2 = new Soba(2, "dvosobna", 2, 8999.99);
		Soba soba3 = new Soba(3, "jednosobna", 1, 3499.99);
		Soba soba4 = new Soba(4, "trosobna", 5, 12499.99);
		Soba soba5 = new Soba();
		
		sobe.add(soba1);
		sobe.add(soba2);
		sobe.add(soba3);
		sobe.add(soba4);
		sobe.add(soba5);
		
		Rezervacija rezervacija1 = new Rezervacija(1, LocalDateTime.now(), "Pera Peric");
		Rezervacija rezervacija2 = new Rezervacija(2, LocalDateTime.now(), "Marko Markovic");
		Rezervacija rezervacija3 = new Rezervacija(3, LocalDateTime.now(), "Bora Boric");
		Rezervacija rezervacija4 = new Rezervacija(4, LocalDateTime.now(), "Jovan Jovanovic");
		Rezervacija rezervacija5 = new Rezervacija();
		
		rezervacije.add(rezervacija1);
		rezervacije.add(rezervacija2);
		rezervacije.add(rezervacija3);
		rezervacije.add(rezervacija4);
		rezervacije.add(rezervacija5);
		
		soba1.dodajSadrzaj(sadrzajSobe2);
		soba2.dodajSadrzaj(sadrzajSobe3);
		soba3.dodajSadrzaj(sadrzajSobe4);
		soba4.dodajSadrzaj(sadrzajSobe1);
		soba5.dodajSadrzaj(sadrzajSobe5);
		
		rezervacija1.dodajSobu(soba1);
		rezervacija2.dodajSobu(soba2);
		rezervacija2.dodajSobu(soba3);
		rezervacija3.dodajSobu(soba4);
		rezervacija4.dodajSveSobe(sobe);
		rezervacija5.dodajSobu(soba5);
		
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		inicijalizacija();
		
		for(Prenociste p : prenocista) {
			System.out.println(p);
		}
		System.out.println("-----------------------------");
		for(SadrzajSobe ss : sadrzajiSoba) {
			System.out.println(ss);
		}
		System.out.println("-----------------------------");
		for(Soba s : sobe) {
			System.out.println(s);
		}
		System.out.println("-----------------------------");
		for(Rezervacija r : rezervacije) {
			System.out.println(r);
		}
		
		System.out.println("-----------------------------");
		
		String vreme = "11.08.2008. 23:58";
		LocalDateTime datum = stringUDatum(vreme);
		System.out.println(datumUString(datum));

	}

}
