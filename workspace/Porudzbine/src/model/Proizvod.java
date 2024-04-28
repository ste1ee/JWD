package model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Proizvod {

  private long id;
  private String sifra;
  private String naziv;
  private double cena;
  private boolean besplatnadostava;

  private Set<Porudzbina> porudzbine = new LinkedHashSet<>();

  public Proizvod(long id, String sifra, String naziv, double cena, boolean besplatnadostava) {
    this.id = id;
    this.sifra = sifra;
    this.naziv = naziv;
    this.cena = cena;
    this.besplatnadostava = besplatnadostava;
  }

  public Set<Porudzbina> getPorudzbine() {
    return Collections.unmodifiableSet(porudzbine);
  }

  public void addPorudzbina(Porudzbina porudzbina) {
    this.porudzbine.add(porudzbina);
  }
  
  public void addAllPorudzbine(Collection<Porudzbina> porudzbine) {
    this.porudzbine.addAll(porudzbine);
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
    Proizvod other = (Proizvod) obj;
    return id == other.id;
  }



  @Override
  public String toString() {
    return "Proizvod [id=" + id + ", sifra=" + sifra + ", naziv=" + naziv + ", cena=" + cena
        + ", besplatnadostava=" + besplatnadostava + ", porudzbine=" + porudzbine + "]";
  }

  public String getSifra() {
    return sifra;
  }

  public void setSifra(String sifra) {
    this.sifra = sifra;
  }

  public String getNaziv() {
    return naziv;
  }

  public void setNaziv(String naziv) {
    this.naziv = naziv;
  }

  public double getCena() {
    return cena;
  }

  public void setCena(double cena) {
    this.cena = cena;
  }

  public boolean isBesplatnadostava() {
    return besplatnadostava;
  }

  public void setBesplatnadostava(boolean besplatnadostava) {
    this.besplatnadostava = besplatnadostava;
  }

  public long getId() {
    return id;
  }



}
