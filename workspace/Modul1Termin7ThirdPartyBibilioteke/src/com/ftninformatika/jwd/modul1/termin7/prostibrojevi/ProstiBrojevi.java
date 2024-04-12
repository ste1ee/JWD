package com.ftninformatika.jwd.modul1.termin7.prostibrojevi;

public class ProstiBrojevi {

	private static final int KOLIKO = 10;

	public static void main(String[] args) {
		System.out.print("Prvih " + KOLIKO + " prostih brojeva: ");

		int sledeci = 1;
		for (int it = 0; it < KOLIKO; it++) {
			System.out.print(sledeci + " ");
//			sledeci = Primes.nextPrime(sledeci + 1);
		}

	}

}
