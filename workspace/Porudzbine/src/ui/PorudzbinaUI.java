package ui;

import java.time.LocalDateTime;
import java.util.Collection;
import dao.PorudzbinaDAO;
import dao.ProizvodDAO;
import model.Porudzbina;
import model.Proizvod;
import util.Konzola;

public class PorudzbinaUI {
  
  private static PorudzbinaDAO porudzbinaDAO;
  private static ProizvodDAO proizvodDAO;

  public static void setPorudzbinaDAO(PorudzbinaDAO porudzbinaDAO) {
      PorudzbinaUI.porudzbinaDAO = porudzbinaDAO;
  }
  
  public static void setProizvodDAO(ProizvodDAO proizvodDao) {
    PorudzbinaUI.proizvodDAO = proizvodDAO;
  }
  
  public static void prikazSvih() {
    try {
      for(Proizvod itP : proizvodDAO.getAll()) {
        Collection<Porudzbina> porudzbine = porudzbinaDAO.find(itP);
        
        System.out.println();
        for (Porudzbina p : porudzbine) {
          System.out.println(p);
        }
      }
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("Došlo je do greške!");
    }
  }
  
  public static void dodavanje() {
    try {
        Proizvod proizvod = ProizvodUI.pronalazenje();
        if (proizvod == null) {
            return;
        }
        String ulica = Konzola.ocitajString("Unesite ulicu");
        int broj = Konzola.ocitajInt("Unesite broj");

        LocalDateTime datumIVreme = LocalDateTime.now();
        Porudzbina porudzbina = new Porudzbina(0, datumIVreme, ulica, broj, proizvod);
        porudzbinaDAO.add(porudzbina);
    } catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("Došlo je do greške!");
    }
  }

}
