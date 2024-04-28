package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import dao.PorudzbinaDAO;
import dao.ProizvodDAO;
import dao.impl.PorudzbinadatabaseDAO;
import dao.impl.ProizvoddatabaseDAO;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class App {

  private static void initDatabase() throws Exception {
    Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/porudzbine?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade",
        "root", "root");

    PorudzbinaDAO porudzbinaDAO = new PorudzbinadatabaseDAO(conn);
    ProizvodDAO proizvodDAO = new ProizvoddatabaseDAO(conn, porudzbinaDAO);

    PorudzbinaUI.setPorudzbinaDAO(porudzbinaDAO);
    PorudzbinaUI.setProizvodDAO(proizvodDAO);
    ProizvodUI.setProizvodDAO(proizvodDAO);
    // IzvestavanjeUI.setVoziloDAO(voziloDAO);
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
    Meni.pokreni("Taxi", new StavkaMenija[] {new IzlaznaStavkaMenija("Izlaz"),
        new FunkcionalnaStavkaMenija("Prikaz svih proizvoda") {

          @Override
          public void izvrsi() {
            ProizvodUI.prikazSvih();
          }

        }, new FunkcionalnaStavkaMenija("Prikaz svih porudzbina sa proizvodima") {

          @Override
          public void izvrsi() {
            PorudzbinaUI.prikazSvih();
          }

        }, new FunkcionalnaStavkaMenija("Dodavanje porudzbine") {

          @Override
          public void izvrsi() {
            PorudzbinaUI.dodavanje();
          }

        }, new FunkcionalnaStavkaMenija("Izvestavanje") {

          @Override
          public void izvrsi() {
            //IzvestavanjeUI.izvestavanje();
          }

        }});
  }

}
