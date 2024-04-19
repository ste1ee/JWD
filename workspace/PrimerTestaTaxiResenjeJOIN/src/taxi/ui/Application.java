package taxi.ui;

import java.sql.Connection;
import java.sql.DriverManager;

import com.ftninformatika.jwd.modul1.util.Meni;
import com.ftninformatika.jwd.modul1.util.Meni.FunkcionalnaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.IzlaznaStavkaMenija;
import com.ftninformatika.jwd.modul1.util.Meni.StavkaMenija;

import taxi.model.dao.PozivDAO;
import taxi.model.dao.VoziloDAO;
import taxi.model.dao.impl.DatabasePozivDAO;
import taxi.model.dao.impl.DatabaseVoziloDAO;

public class Application {

	private static void initDatabase() throws Exception {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/taxi?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade", 
				"root", 
				"root");

		VoziloDAO voziloDAO = new DatabaseVoziloDAO(conn);
		PozivDAO pozivDAO = new DatabasePozivDAO(conn);
		VozilaUI.setVoziloDAO(voziloDAO);
		PozivUI.setPozivDAO(pozivDAO);
		IzvestavanjeUI.setVoziloDAO(voziloDAO);
	}

	static {
		try {
			initDatabase();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Greška pri povezivanju sa izvorom podataka!");
			
			System.exit(1);
		}
	}

	public static void main(String[] args) throws Exception {
		Meni.pokreni("Taxi", new StavkaMenija[] {
			new IzlaznaStavkaMenija("Izlaz"),
			new FunkcionalnaStavkaMenija("Prikaz svih vozila") {

				@Override
				public void izvrsi() { VozilaUI.prikazSvih(); }
				
			}, 
			new FunkcionalnaStavkaMenija("Prikaz jednog vozila sa pozivima") {

				@Override
				public void izvrsi() { VozilaUI.prikaz(); }
				
			}, 
			new FunkcionalnaStavkaMenija("Dodavanje poziva") {

				@Override
				public void izvrsi() { PozivUI.dodavanje(); }
				
			}, 
			new FunkcionalnaStavkaMenija("Izvestavanje") {

				@Override
				public void izvrsi() { IzvestavanjeUI.izvestavanje(); }
				
			}
		});
	}

}
