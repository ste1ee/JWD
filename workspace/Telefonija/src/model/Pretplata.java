package model;

import java.time.LocalDate;
import java.util.Objects;

public class Pretplata {

  private long id;
  private String broj;
  private LocalDate datum;
  private int trajanje;

  Tarifa tarifa;

  public Pretplata(long id, String broj, LocalDate datum, int trajanje, Tarifa tarifa) {
    this.id = id;
    this.broj = broj;
    this.datum = datum;
    this.trajanje = trajanje;
    if (tarifa != null) {
      this.tarifa = tarifa;
      tarifa.addPretplata(this);
    }
  }

  @Override
  public String toString() {
    return "Pretplata [id=" + id + ", broj=" + broj + ", datum=" + datum + ", trajanje=" + trajanje
        + ", tarifa=" + tarifa.getNaziv() + ", ukupna cena=" + tarifa.getCena() * trajanje + "]";
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Pretplata other = (Pretplata) obj;
    return id == other.id;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBroj() {
    return broj;
  }

  public void setBroj(String broj) {
    this.broj = broj;
  }

  public LocalDate getDatum() {
    return datum;
  }

  public void setDatum(LocalDate datum) {
    this.datum = datum;
  }

  public int getTrajanje() {
    return trajanje;
  }

  public void setTrajanje(int trajanje) {
    this.trajanje = trajanje;
  }

  public Tarifa getTarifa() {
    return tarifa;
  }

  public void setTarifa(Tarifa tarifa) {
    if (tarifa != null) {
      this.tarifa = tarifa;
      tarifa.addPretplata(this);
    }
  }



}
