package ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import dao.TarifaDAO;
import model.Pretplata;
import model.Tarifa;
import util.Konzola;

public class IzvestavanjeUI {

  private static TarifaDAO tarifaDAO;

  public static void setTarifaDAO(TarifaDAO tarifaDAO) {
    IzvestavanjeUI.tarifaDAO = tarifaDAO;
  }

  public static void izvestavanje() {
    LocalDate pocetak = Konzola.ocitajDate("Unesite pocetni datum");
    LocalDate kraj = Konzola.ocitajDate("Unesite krajnji datum");

    try {
      ArrayList<Tarifa> tarife = (ArrayList<Tarifa>) tarifaDAO.getAll();
      for (Tarifa t : tarife) {
        System.out.print(t.getNaziv());
        Collection<Pretplata> pretplate = t.getPretplate();
        ArrayList<Integer> trajanja = new ArrayList<>();
        double ukupanPrihodTarife = 0;
        for (Pretplata p : pretplate) {
          double ukupanPrihodPretplate = p.getTrajanje() * t.getCena();
          if (p.getDatum().isAfter(pocetak) && p.getDatum().isBefore(kraj)) {
            ukupanPrihodTarife += ukupanPrihodTarife;
          }
          trajanja.add(p.getTrajanje());
        }
        System.out.print(", " + ukupanPrihodTarife);

        Map<Integer, Long> elementCountMap = trajanja.stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Integer> result = elementCountMap.values().stream().max(Long::compareTo)
            .map(maxValue -> elementCountMap.entrySet().stream()
                .filter(entry -> maxValue.equals(entry.getValue())).map(Map.Entry::getKey)
                .collect(Collectors.toList()))
            .orElse(Collections.emptyList());
        
        System.out.print(", " + result);
        System.out.println();
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
