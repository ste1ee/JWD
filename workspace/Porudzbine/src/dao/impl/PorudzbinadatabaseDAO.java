package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import dao.PorudzbinaDAO;
import model.Porudzbina;
import model.Proizvod;

public class PorudzbinadatabaseDAO implements PorudzbinaDAO {
  
  private final Connection conn;
  
  public PorudzbinadatabaseDAO(Connection conn) {
    this.conn = conn;
  }

  @Override
  public Collection<Porudzbina> find(Proizvod proizvod) throws Exception {
    Collection<Porudzbina> porudzbine = new ArrayList<>();
    String sql =
        "SELECT id, datum, ulica, broj FROM porudzbine WHERE proizvodId = ?";
    try(PreparedStatement stmt = conn.prepareStatement(sql)){
      int p = 0;
      stmt.setLong(++p, proizvod.getId());
      try(ResultSet rset = stmt.executeQuery()){
        while(rset.next()) {
          int k = 0;
          long id = rset.getLong(++k);
          LocalDateTime datum = rset.getTimestamp(++k).toLocalDateTime();
          String ulica = rset.getString(++k);
          int broj = rset.getInt(++k);
          
          Porudzbina porudzbina = new Porudzbina(id, datum, ulica, broj, proizvod);
          porudzbine.add(porudzbina);
        }
      }
    }
    return porudzbine;
  }

  @Override
  public void add(Porudzbina porudzbina) throws Exception {
    String sql =
        "INSERT INTO porudzbine (datum, ulica, broj, proizvodId) VALUES (?, ?, ?, ?)";
    try(PreparedStatement stmt = conn.prepareStatement(sql)){
      int p = 0;
      stmt.setTimestamp(++p, Timestamp.valueOf(porudzbina.getDatum()));
      stmt.setString(++p, porudzbina.getUlica());
      stmt.setInt(++p, porudzbina.getBroj());
      stmt.setLong(++p, porudzbina.getProizvod().getId());
      
      stmt.executeUpdate();
    }

  }

  /*
   * @Override public Collection<Porudzbina> getAll() throws Exception { Collection<Porudzbina>
   * porudzbine = new ArrayList<>(); String sql =
   * "SELECT id, datum, ulica, broj, proizvodId FROM porudzbine"; try(PreparedStatement stmt =
   * conn.prepareStatement(sql)){ try(ResultSet rset = stmt.executeQuery()){ while(rset.next()) {
   * int k = 0; long id = rset.getLong(++k); LocalDateTime datum =
   * rset.getTimestamp(++k).toLocalDateTime(); String ulica = rset.getString(++k); int broj =
   * rset.getInt(++k); long proizvodId = rset.getLong(++k); Proizvod proizvod =
   * proizvodDAO.get(proizvodId);
   * 
   * Porudzbina porudzbina = new Porudzbina(id, datum, ulica, broj, proizvod);
   * porudzbine.add(porudzbina); } } } return porudzbine; }
   */

}
