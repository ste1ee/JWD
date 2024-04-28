package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import dao.TarifaDAO;
import model.Tarifa;

public class DatabaseTarifaDAO implements TarifaDAO {

  private final Connection conn;

  public DatabaseTarifaDAO(Connection conn) {
    this.conn = conn;
  }

  @Override
  public Collection<Tarifa> getAll() throws Exception {
    Collection<Tarifa> tarife = new ArrayList<>();
    String sql = "SELECT id, naziv, opis, cena FROM tarife";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      try (ResultSet rset = stmt.executeQuery()) {
        while (rset.next()) {
          int k = 0;
          long id = rset.getLong(++k);
          String naziv = rset.getString(++k);
          String opis = rset.getString(++k);
          double cena = rset.getDouble(++k);

          Tarifa tarifa = new Tarifa(id, naziv, opis, cena);
          tarife.add(tarifa);
        }
      }
    }
    return tarife;
  }

  @Override
  public Tarifa get(long id) throws Exception {
    Tarifa tarifa = null;
    String sql = "SELECT naziv, opis, cena FROM tarife WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      int p = 0;
      stmt.setLong(++p, id);
      try (ResultSet rset = stmt.executeQuery()) {
        while (rset.next()) {
          int k = 0;
          String naziv = rset.getString(++k);
          String opis = rset.getString(++k);
          double cena = rset.getDouble(++k);

          tarifa = new Tarifa(id, naziv, opis, cena);
        }
      }
    }
    return tarifa;
  }

}
