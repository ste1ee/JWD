package dao;

import java.util.Collection;
import model.Porudzbina;
import model.Proizvod;

public interface PorudzbinaDAO {
  
  //public Collection<Porudzbina> getAll() throws Exception;
  public Collection<Porudzbina> find(Proizvod proizvod) throws Exception;
  public void add(Porudzbina porudzbina) throws Exception;

}
