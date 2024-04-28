package ui;

import java.time.LocalDate;
import java.util.Collection;
import dao.PretplataDAO;
import model.Pretplata;
import model.Tarifa;
import util.Konzola;

public class PretplataUI {

  private static PretplataDAO pretplataDAO;

  public static void setPretplataDAO(PretplataDAO pretplataDAO) {
    PretplataUI.pretplataDAO = pretplataDAO;
  }
  
  public static void prikazSvih() {
    try {
        Collection<Pretplata> pretplate = pretplataDAO.getAll();

        System.out.println();
        for (Pretplata p : pretplate) {
            System.out.println(p);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("Došlo je do greške!");
    }
  }
  
  public static void dodavanje() {
    try {
        Tarifa tarifa = TarifaUI.pronalazenje();
        if (tarifa == null) {
            return;
        }
        String broj = Konzola.ocitajString("Unesite pretplatnicki broj");
        if(pretplataDAO.getPoBroju(broj) != null)
          return;
        LocalDate datum = Konzola.ocitajDate("Unesite datum pocetka");
        int trajanje = Konzola.ocitajInt("Unesite trajanje u mesecima");

        Pretplata pretplata = new Pretplata(0, broj, datum, trajanje, tarifa);
        pretplataDAO.add(pretplata);
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("Došlo je do greške!");
    }
  }

}
