package com.ftninformatika.jwd.modul1.ui;

import java.io.IOException;

import com.ftninformatika.jwd.modul1.uti.model.Prodavnica;
import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;

public class Application {

	public static void main(String[] args) throws Exception {
		Prodavnica.ucitaj();
		
		Meni.pokreni("Prodavnica", new StavkaMenija[] {
				
			new IzlaznaStavkaMenija("Izlaz"),
			new FunkcionalnaStavkaMenija("Kategorija") {
				
				@Override
				public void izvrsi() {
					KategorijaUI.meni();
					
				}
			},
			new FunkcionalnaStavkaMenija("Proizvod") {
				
				@Override
				public void izvrsi() {
					// TODO Auto-generated method stub
					
				}
			},
			new FunkcionalnaStavkaMenija("Stavka") {
				
				@Override
				public void izvrsi() {
					// TODO Auto-generated method stub
					
				}
			},
			new FunkcionalnaStavkaMenija("Racun") {
				
				@Override
				public void izvrsi() {
					// TODO Auto-generated method stub
					
				}
			},
			
				
				
				
				
		});
	
		
	}

}
