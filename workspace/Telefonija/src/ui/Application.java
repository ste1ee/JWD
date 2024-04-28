package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import dao.PretplataDAO;
import dao.TarifaDAO;
import dao.impl.DatabasePretplataDAO;
import dao.impl.DatabaseTarifaDAO;
import util.Meni;
import util.Meni.FunkcionalnaStavkaMenija;
import util.Meni.IzlaznaStavkaMenija;
import util.Meni.StavkaMenija;

public class Application {
  private static void initDatabase() throws Exception {
    Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/telefonija?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Europe/Belgrade",
        "root", "root");

    TarifaDAO tarifaDAO = new DatabaseTarifaDAO(conn);
    PretplataDAO pretplataDAO = new DatabasePretplataDAO(conn);
    TarifaUI.setTarifaDAO(tarifaDAO);
    PretplataUI.setPretplataDAO(pretplataDAO);
    IzvestavanjeUI.setTarifaDAO(tarifaDAO);
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
    Meni.pokreni("Telefonija", new StavkaMenija[] {new IzlaznaStavkaMenija("Izlaz"),
        new FunkcionalnaStavkaMenija("Prikaz svih tarifa") {

          @Override
          public void izvrsi() {
            TarifaUI.prikazSvih();
          }

        }, new FunkcionalnaStavkaMenija("Prikaz svih pretplata sa tarifama") {

          @Override
          public void izvrsi() {
            PretplataUI.prikazSvih();
          }

        }, new FunkcionalnaStavkaMenija("Dodavanje pretplate") {

          @Override
          public void izvrsi() {
            PretplataUI.dodavanje();
          }

        }, new FunkcionalnaStavkaMenija("Izvestavanje") {

          @Override
          public void izvrsi() {
            IzvestavanjeUI.izvestavanje();
          }

        }});
  }
}
