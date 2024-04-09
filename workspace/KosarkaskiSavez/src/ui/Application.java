package ui;

import java.io.IOException;

import model.Savez;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class Application {

	public static void main(String[] args) throws IOException {
		Savez.ucitaj();

		Meni.pokreni("Kosarkaski savez", new StavkaMenija[] {

				new IzlaznaStavkaMenija("Izlaz"), new FunkcionalnaStavkaMenija("Igrac") {

					@Override
					public void izvrsi() {
						IgracUI.meni();

					}
				}, new FunkcionalnaStavkaMenija("Klub") {

					@Override
					public void izvrsi() {
						KlubUI.meni();

					}
				}, new FunkcionalnaStavkaMenija("Sudija") {

					@Override
					public void izvrsi() {
						SudijaUI.meni();

					}
				}, new FunkcionalnaStavkaMenija("Utakmica") {

					@Override
					public void izvrsi() {
						UtakmicaUI.meni();

					}
				},

		});

	}

}
