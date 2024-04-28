package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import dao.PorudzbinaDAO;
import dao.ProizvodDAO;
import model.Porudzbina;
import model.Proizvod;

public class ProizvoddatabaseDAO implements ProizvodDAO {
  
  private final Connection conn;
  private PorudzbinaDAO porudzbinaDAO;
  
  public ProizvoddatabaseDAO(Connection conn, PorudzbinaDAO porudzbinaDAO) {
    this.conn = conn;
    this.porudzbinaDAO = porudzbinaDAO;
  }

  @Override
  public Collection<Proizvod> getAll() throws Exception {
    Collection<Proizvod> proizvodi = new ArrayList<>();
    String sql =
        "SELECT id, sifra, naziv, cena, besplatnadostava FROM proizvodi";
    try(PreparedStatement stmt = conn.prepareStatement(sql)){
      try(ResultSet rset = stmt.executeQuery()){
        while(rset.next()) {
          int k = 0;
          long id = rset.getLong(++k);
          String sifra = rset.getString(++k);
          String naziv = rset.getString(++k);
          double cena = rset.getDouble(++k);
          boolean besplatnadostava = rset.getBoolean(++k);
          
          Proizvod proizvod = new Proizvod(id, sifra, naziv, cena, besplatnadostava);
          proizvodi.add(proizvod);
          
          Collection<Porudzbina> porudzbine = porudzbinaDAO.find(proizvod);
          proizvod.addAllPorudzbine(porudzbine);
        }
      }
    }
    
    return proizvodi;
  }

  @Override
  public Proizvod get(long id) throws Exception {
    Proizvod proizvod = null;
    String sql=
        "SELECT sifra, naziv, cena, besplatnadostava FROM proizvodi WHERE id = ?";
    try(PreparedStatement stmt = conn.prepareStatement(sql)){
      int p = 0;
      stmt.setLong(++p, id);
      try(ResultSet rset = stmt.executeQuery()){
        while(rset.next()) {
          int k = 0;
          String sifra = rset.getString(++k);
          String naziv = rset.getString(++k);
          double cena = rset.getDouble(++k);
          boolean besplatnadostava = rset.getBoolean(++k);
          
          proizvod = new Proizvod(id, sifra, naziv, cena, besplatnadostava);
          
          Collection<Porudzbina> porudzbine = porudzbinaDAO.find(proizvod);
          proizvod.addAllPorudzbine(porudzbine);
        }
      }
    }
    return proizvod;
  }

  @Override
  public Proizvod getSifrom(String sifra) throws Exception {
    Proizvod proizvod = null;
    String sql=
        "SELECT id, naziv, cena, besplatnadostava FROM proizvodi WHERE sifra = ?";
    try(PreparedStatement stmt = conn.prepareStatement(sql)){
      int p = 0;
      stmt.setString(++p, sifra);
      try(ResultSet rset = stmt.executeQuery()){
        while(rset.next()) {
          int k = 0;
          long id = rset.getLong(++k);
          String naziv = rset.getString(++k);
          double cena = rset.getDouble(++k);
          boolean besplatnadostava = rset.getBoolean(++k);
          
          proizvod = new Proizvod(id, sifra, naziv, cena, besplatnadostava);
          
          Collection<Porudzbina> porudzbine = porudzbinaDAO.find(proizvod);
          proizvod.addAllPorudzbine(porudzbine);
        }
      }
    }
    return proizvod;
  }

}
