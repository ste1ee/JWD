package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Izvestaj {
	
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";
	
	static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
	
	private static final Map<Long, Vozilo> vozila = new HashMap<>();
	private static final Map<Long, Poziv> pozivi = new HashMap<>();
	
	
	private static void inicijalizuj() {
		vozila.put(1L, new Vozilo(1, 101, "Petar Peric"));
		vozila.put(2L, new Vozilo(2, 102, "Sinisa Sinis"));
		vozila.put(3L, new Vozilo(3, 103, "Jovan Jovanovic"));
		
		pozivi.put(1L, new Poziv(1, LocalDateTime.parse("2021-09-01 10:00", DATE_TIME_FORMATTER), "Bul. Oslobođenja", 10, vozila.get(1L)));
		pozivi.put(1L, new Poziv(1, LocalDateTime.parse("2021-09-01 14:00", DATE_TIME_FORMATTER), "Bul. Mihajla Pupina", 10, vozila.get(1L)));
		pozivi.put(1L, new Poziv(1, LocalDateTime.parse("2021-09-01 20:00", DATE_TIME_FORMATTER), "Bul. Oslobođenja", 20, vozila.get(2L)));
		pozivi.put(1L, new Poziv(1, LocalDateTime.parse("2021-09-02 10:30", DATE_TIME_FORMATTER), "Bul. Cara Lazara", 10, vozila.get(1L)));
		pozivi.put(1L, new Poziv(1, LocalDateTime.parse("2021-09-02 14:30", DATE_TIME_FORMATTER), "Bul. Oslobođenja", 30, vozila.get(3L)));
		pozivi.put(1L, new Poziv(1, LocalDateTime.parse("2021-09-03 10:00", DATE_TIME_FORMATTER), "Bul. Mihajla Pupina", 20, vozila.get(2L)));
		pozivi.put(1L, new Poziv(1, LocalDateTime.parse("2021-09-03 14:00", DATE_TIME_FORMATTER), "Bul. Mihajla Pupina", 30, vozila.get(3L)));
		pozivi.put(1L, new Poziv(1, LocalDateTime.parse("2021-09-03 20:00", DATE_TIME_FORMATTER), "Bul. Cara Lazara", 20, vozila.get(1L)));
		
	}
	
	public static Map<Long, Vozilo> getVozila(){
		return vozila;
	}
	
	public static Map<Long, Poziv> getPozivi(){
		return pozivi;
	}

}
