package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import dao.PretplataDAO;
import model.Pretplata;
import model.Tarifa;

public class DatabasePretplataDAO implements PretplataDAO {

  private final Connection conn;

  public DatabasePretplataDAO(Connection conn) {
    this.conn = conn;
  }

  @Override
  public Collection<Pretplata> getAll() throws Exception {
    Collection<Pretplata> pretplate = new ArrayList<>();
    String sql =
        "SELECT p.id, p.broj, p.datum, p.trajanje, t.id, t.naziv, t.opis, t.cena FROM pretplate p\r\n"
            + "JOIN tarife t on t.id = p.tarifaId";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      try (ResultSet rset = stmt.executeQuery()) {
        while (rset.next()) {
          int k = 0;
          long pId = rset.getLong(++k);
          String pBroj = rset.getString(++k);
          LocalDate pDatum = rset.getDate(++k).toLocalDate();
          int pTrajanje = rset.getInt(++k);

          long tId = rset.getLong(++k);
          String tNaziv = rset.getString(++k);
          String tOpis = rset.getString(++k);
          double tCena = rset.getDouble(++k);

          Tarifa tarifa = new Tarifa(tId, tNaziv, tOpis, tCena);

          Pretplata pretplata = new Pretplata(pId, pBroj, pDatum, pTrajanje, tarifa);
          pretplate.add(pretplata);
        }
      }
    }

    return pretplate;
  }

  @Override
  public Pretplata get(long id) throws Exception {
    Pretplata pretplata = null;
    String sql =
        "SELECT p.broj, p.datum, p.trajanje, t.id, t.naziv, t.opis, t.cena FROM pretplate p\r\n"
            + "JOIN tarife t on t.id = p.tarifaId WHERE p.id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      int p = 0;
      stmt.setLong(++p, id);
      try (ResultSet rset = stmt.executeQuery()) {
        while (rset.next()) {
          int k = 0;
          String pBroj = rset.getString(++k);
          LocalDate pDatum = rset.getDate(++k).toLocalDate();
          int pTrajanje = rset.getInt(++k);

          long tId = rset.getLong(++k);
          String tNaziv = rset.getString(++k);
          String tOpis = rset.getString(++k);
          double tCena = rset.getDouble(++k);

          Tarifa tarifa = new Tarifa(tId, tNaziv, tOpis, tCena);

          pretplata = new Pretplata(id, pBroj, pDatum, pTrajanje, tarifa);
        }
      }
    }
    return pretplata;
  }

  @Override
  public void add(Pretplata pretplata) throws Exception {
    String sql =
        "INSERT INTO pretplate (id, tarifaId, broj, datum, trajanje) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      int p = 0;
      stmt.setLong(++p, pretplata.getId());
      stmt.setLong(++p, pretplata.getTarifa().getId());
      stmt.setString(++p, pretplata.getBroj());
      stmt.setDate(++p, Date.valueOf(pretplata.getDatum()));
      stmt.setInt(++p, pretplata.getTrajanje());

      stmt.executeUpdate();
    }

  }

  @Override
  public Pretplata getPoBroju(String broj) throws Exception {
    Pretplata pretplata = null;
    String sql =
        "SELECT p.id, p.datum, p.trajanje, t.id, t.naziv, t.opis, t.cena FROM pretplate p\r\n"
            + "JOIN tarife t on t.id = p.tarifaId WHERE p.broj = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      int p = 0;
      stmt.setString(++p, broj);
      try (ResultSet rset = stmt.executeQuery()) {
        while (rset.next()) {
          int k = 0;
          Long pId = rset.getLong(++k);
          LocalDate pDatum = rset.getDate(++k).toLocalDate();
          int pTrajanje = rset.getInt(++k);

          long tId = rset.getLong(++k);
          String tNaziv = rset.getString(++k);
          String tOpis = rset.getString(++k);
          double tCena = rset.getDouble(++k);

          Tarifa tarifa = new Tarifa(tId, tNaziv, tOpis, tCena);

          pretplata = new Pretplata(pId, broj, pDatum, pTrajanje, tarifa);
        }
      }
    }
    return pretplata;
  }

}
