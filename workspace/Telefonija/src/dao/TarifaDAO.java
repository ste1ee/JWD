package dao;

import java.util.Collection;
import model.Tarifa;

public interface TarifaDAO {
  
  public Collection<Tarifa> getAll() throws Exception;
  public Tarifa get(long id) throws Exception;

}
