package dao;

import java.util.Collection;
import model.Proizvod;

public interface ProizvodDAO {
  
  public Collection<Proizvod> getAll() throws Exception;
  public Proizvod get(long id) throws Exception;
  public Proizvod getSifrom(String sifra) throws Exception;

}
