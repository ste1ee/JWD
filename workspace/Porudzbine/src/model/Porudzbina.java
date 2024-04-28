package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Porudzbina {

  private long id;
  private LocalDateTime datum;
  private String ulica;
  private int broj;

  private Proizvod proizvod;

  public Porudzbina(long id, LocalDateTime datum, String ulica, int broj, Proizvod proizvod) {
    this.id = id;
    this.datum = datum;
    this.ulica = ulica;
    this.broj = broj;
    this.proizvod = proizvod;
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
    Porudzbina other = (Porudzbina) obj;
    return id == other.id;
  }



  @Override
  public String toString() {
    return "Porudzbina [id=" + id + ", datum=" + datum + ", ulica=" + ulica + ", broj=" + broj
        + ", proizvod=" + proizvod + "]";
  }



  public LocalDateTime getDatum() {
    return datum;
  }

  public void setDatum(LocalDateTime datum) {
    this.datum = datum;
  }

  public String getUlica() {
    return ulica;
  }

  public void setUlica(String ulica) {
    this.ulica = ulica;
  }

  public int getBroj() {
    return broj;
  }

  public void setBroj(int broj) {
    this.broj = broj;
  }

  public Proizvod getProizvod() {
    return proizvod;
  }

  public void setProizvod(Proizvod proizvod) {
    this.proizvod = proizvod;
  }

  public long getId() {
    return id;
  }



}
