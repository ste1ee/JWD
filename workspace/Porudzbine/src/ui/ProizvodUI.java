package ui;

import java.util.Collection;
import dao.ProizvodDAO;
import model.Proizvod;
import util.Konzola;

public class ProizvodUI {
  
  private static ProizvodDAO proizvodDAO;

  public static void setProizvodDAO(ProizvodDAO proizvodDAO) {
      ProizvodUI.proizvodDAO = proizvodDAO;
  }
  
  public static Proizvod pronalazenje() throws Exception {
    prikazSvih();

    String sifra = Konzola.ocitajString("Unesite sifru proizvoda");

    Proizvod p = proizvodDAO.getSifrom(sifra);
    if (p == null)
        Konzola.prikazi("Proizvod nije pronađen!");

    return p;
  }
  
  public static void prikazSvih() {
    try {
        Collection<Proizvod> proizvodi = proizvodDAO.getAll();

        System.out.println();
        for (Proizvod p: proizvodi) {
            System.out.println(p);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("Došlo je do greške!");
    }
  }
  
}
