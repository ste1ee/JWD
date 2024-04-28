package model;

import java.time.LocalDate;
import java.util.Objects;

public class Clanarina {

  private long id;

  private Paket paket;

  private String korisnickoIme;
  private LocalDate pocetak;

  public Clanarina(long id, Paket paket, String korisnickoIme, LocalDate pocetak) {
    this.id = id;
    this.paket = paket;
    this.korisnickoIme = korisnickoIme;
    this.pocetak = pocetak;
  }

  public Clanarina(Paket paket, String korisnickoIme, LocalDate pocetak) {
    this(0, paket, korisnickoIme, pocetak);
  }

  @Override
  public String toString() {
    return "Clanarina [id=" + id + ", paket=" + paket.getNaziv() + ", datum isteka="
        + pocetak.plusMonths(paket.getMeseci()) + ", korisnickoIme=" + korisnickoIme + ", pocetak="
        + pocetak + "]";
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
    Clanarina other = (Clanarina) obj;
    return id == other.id;
  }

  public long getId() {
    return id;
  }

  public Paket getPaket() {
    return paket;
  }

  public void setPaket(Paket paket) {
    if (paket != null)
      this.paket = paket;
  }

  public String getKorisnickoIme() {
    return korisnickoIme;
  }

  public void setKorisnickoIme(String korisnickoIme) {
    this.korisnickoIme = korisnickoIme;
  }

  public LocalDate getPocetak() {
    return pocetak;
  }

  public void setPocetak(LocalDate pocetak) {
    this.pocetak = pocetak;
  }

}
