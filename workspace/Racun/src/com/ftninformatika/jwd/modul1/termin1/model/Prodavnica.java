package com.ftninformatika.jwd.modul1.termin1.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class Prodavnica {
	
	
	private static final String DATE_TIME_FORMAT = "dd.MM.yyyy. HH:mm";
	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
	
	public static String datumUString(LocalDateTime datum) {
		return DATE_TIME_FORMATTER.format(datum);
	}
	
	public static LocalDateTime stringUDatum(String datum) {
		return LocalDateTime.parse(datum, DATE_TIME_FORMATTER);
	}
	
	
	private static final Set<Kategorija> kategorije = new HashSet<>();
	private static final Set<Proizvod> proizvodi = new HashSet<>();
	private static final Set<Racun> racuni = new HashSet<>();
	private static final Set<Stavka> stavke = new HashSet<>();

	
	private static void da() {
		
		Kategorija kategorija1 = new Kategorija(1, "Decija garderoba");
		Kategorija kategorija2 = new Kategorija(2, "Muska garderoba");
		Kategorija kategorija3 = new Kategorija(3, "Zenska garderoba");
		Kategorija kategorija4 = new Kategorija(4, "Decije cipele");
		Kategorija kategorija5 = new Kategorija(5, "Muske cipele");
		
		kategorije.add(kategorija1);
		kategorije.add(kategorija2);
		kategorije.add(kategorija3);
		kategorije.add(kategorija4);
		kategorije.add(kategorija5);
		
		Proizvod proizvod1 = new Proizvod(1, "Decije pantalone", 1999.99);
		Proizvod proizvod2 = new Proizvod(2, "Muska jakna", 5459.99);
		Proizvod proizvod3 = new Proizvod(3, "Zenska haljina", 3299.99);
		Proizvod proizvod4 = new Proizvod(4, "Muske patike par", 7199.99);
		Proizvod proizvod5 = new Proizvod(5, "Zenske sandale par", 2354.00);
		Proizvod proizvod6 = new Proizvod(6, "Muska majica", 840.00);
		Proizvod proizvod7 = new Proizvod(7, "Decija jakna", 2899.99);
		Proizvod proizvod8 = new Proizvod(8, "Zenske pantalone", 1699.99);
		Proizvod proizvod9 = new Proizvod(9, "Zenska marama", 359.99);
		Proizvod proizvod10 = new Proizvod(10, "Muske carape par", 120.00);
		
		proizvodi.add(proizvod1);
		proizvodi.add(proizvod2);
		proizvodi.add(proizvod3);
		proizvodi.add(proizvod4);
		proizvodi.add( proizvod5);
		proizvodi.add( proizvod6);
		proizvodi.add( proizvod7);
		proizvodi.add( proizvod8);
		proizvodi.add( proizvod9);
		proizvodi.add( proizvod10);
		
		Racun racun1 = new Racun(1, LocalDateTime.now(), 0.0);
		Racun racun2 = new Racun(2, LocalDateTime.now(), 0.0);
		Racun racun3 = new Racun(3, LocalDateTime.now(), 0.0);
		Racun racun4 = new Racun(4, LocalDateTime.now(), 0.0);
		Racun racun5 = new Racun(5, LocalDateTime.now(), 0.0);
		
		racuni.add( racun1);
		racuni.add( racun2);
		racuni.add( racun3);
		racuni.add( racun4);
		racuni.add( racun5);
		
		Stavka stavka1 = new Stavka(1, 1, proizvod1);
		Stavka stavka2 = new Stavka(2, 2, proizvod2);
		Stavka stavka3 = new Stavka(3, 15, proizvod3);
		Stavka stavka4 = new Stavka(4, 1, proizvod4);
		Stavka stavka5 = new Stavka(5, 4, proizvod5);
		Stavka stavka6 = new Stavka(6, 5, proizvod10);
		Stavka stavka7 = new Stavka(7, 2, proizvod6);
		Stavka stavka8 = new Stavka(8, 1, proizvod7);
		Stavka stavka9 = new Stavka(9, 2, proizvod8);
		Stavka stavka10 = new Stavka(10, 1, proizvod9);
		
		stavke.add( stavka1);
		stavke.add( stavka2);
		stavke.add( stavka3);
		stavke.add( stavka4);
		stavke.add( stavka5);
		stavke.add( stavka6);
		stavke.add( stavka7);
		stavke.add( stavka8);
		stavke.add( stavka9);
		stavke.add( stavka10);
		
		proizvod1.dodajKategoriju(kategorija1);
		proizvod2.dodajKategoriju(kategorija2);
		proizvod3.dodajKategoriju(kategorija3);
		proizvod4.dodajKategoriju(kategorija4);
		proizvod5.dodajKategoriju(kategorija5);
		
		

	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		da();
		for(Kategorija k : kategorije) {
			System.out.println(k);
		}
		for(Proizvod p : proizvodi) {
			System.out.println(p);
		}
		for(Stavka s : stavke) {
			System.out.println(s);
		}
		for(Racun r : racuni) {
			System.out.println(r);
		}
		
		System.out.println("-----------------------------");
		
		String vreme = "11.08.2008. 23:58";
		LocalDateTime datum = stringUDatum(vreme);
		System.out.println(datumUString(datum));

	}

}
