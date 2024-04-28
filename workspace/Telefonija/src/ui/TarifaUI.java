package ui;

import java.util.Collection;
import dao.TarifaDAO;
import model.Tarifa;
import util.Konzola;

public class TarifaUI {

  private static TarifaDAO tarifaDAO;

  public static void setTarifaDAO(TarifaDAO tarifaDAO) {
    TarifaUI.tarifaDAO = tarifaDAO;
  }

  public static Tarifa pronalazenje() throws Exception {
    prikazSvih();

    Long id = Konzola.ocitajLong("Unesite id tarife");

    Tarifa tarifa = tarifaDAO.get(id);
    if (tarifa == null)
      Konzola.prikazi("Tarifa nije pronadjena!");

    return tarifa;
  }

  public static void prikazSvih() {
    try {
      Collection<Tarifa> tarife = tarifaDAO.getAll();

      System.out.println();
      for (Tarifa t : tarife) {
        System.out.println(t);
      }
    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("Doslo je do greske!");
    }
  }

  /*
   * public static void prikaz() { try { Tarifa tarifa = pronalazenje(); if (tarifa == null) {
   * return; }
   * 
   * System.out.println(); System.out.println(vozilo); for (Poziv itPoziv : vozilo.getPozivi()) {
   * System.out.println(itPoziv); } } catch (Exception ex) { ex.printStackTrace();
   * System.out.println("Došlo je do greške!"); } }
   */

}
